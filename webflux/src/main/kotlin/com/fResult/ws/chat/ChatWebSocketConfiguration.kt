package com.fResult.ws.chat

import com.fResult.utils.o
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.socket.WebSocketHandler
import org.springframework.web.reactive.socket.WebSocketMessage
import reactor.core.publisher.Flux
import reactor.core.publisher.SignalType
import java.time.Instant
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.Executors
import java.util.concurrent.LinkedBlockingQueue

@Configuration
class ChatWebSocketConfiguration(private val objectMapper: ObjectMapper) {
  val sessions = ConcurrentHashMap<String, Connection>()
  val messages = LinkedBlockingQueue<Message>()

  @Bean
  fun chatWebSocketHandler(): WebSocketHandler {
    val messagesToBroadcast = Flux.create { sink ->
      val submit = Executors.newSingleThreadExecutor()
        .submit {
          while (true) {
            try {
              sink.next(messages.take())
            } catch (ex: InterruptedException) {
              throw RuntimeException(ex)
            }
          }
        }
      sink.onCancel { submit.cancel(true) }
    }.share()

    return WebSocketHandler { session ->
      val sessionID = session.id
      sessions.put(sessionID, Connection(sessionID, session))

      val messagesOffer: (Message) -> Boolean = messages::offer
      val inbound = session.receive()
        .map(::messageToJson o WebSocketMessage::getPayloadAsText)
        .map(messagesOffer o toMessageWithMetadata(sessionID, Instant.now()))
        .doFinally { signalType ->
          if (signalType == SignalType.ON_COMPLETE) {
            sessions.remove(sessionID)
          }
        }

      val outbound = messagesToBroadcast
        .map(objectMapper::writeValueAsString)
        .map(session::textMessage)

      session.send(outbound).and(inbound)
    }
  }

  private fun messageToJson(message: String): Message {
    return try {
      objectMapper.readValue(message, Message::class.java)
    } catch (ex: Exception) {
      throw IllegalArgumentException("Invalid message format", ex)
    }
  }

  private fun toMessageWithMetadata(sessionID: String, `when`: Instant): (Message) -> Message = {
    Message(sessionID, it.text, `when`)
  }
}
