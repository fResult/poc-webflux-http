package com.fResult.ws.chat

import com.fResult.utils.o
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.HandlerMapping
import org.springframework.web.reactive.handler.SimpleUrlHandlerMapping
import org.springframework.web.reactive.socket.WebSocketHandler
import org.springframework.web.reactive.socket.WebSocketMessage
import reactor.core.publisher.Flux
import reactor.core.publisher.FluxSink
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
  fun chatHandlerMapping(): HandlerMapping = SimpleUrlHandlerMapping(mapOf("/ws/chat" to chatWebSocketHandler()), 2)

  @Bean
  fun chatWebSocketHandler(): WebSocketHandler {
    val messagesToBroadcast = Flux.create<Message> { sink ->
      val submit = Executors.newSingleThreadExecutor().use {
        it.submit(::broadcastIncomingMessages)
      }

      sink.onCancel { submit.cancel(true) }
    }.share()

    return WebSocketHandler { session ->
      val sessionID = session.id
      sessions[sessionID] = Connection(sessionID, session)

      val messagesOffer: (Message) -> Boolean = messages::offer
      val inbound = session.receive()
        .map(::jsonToMessage o WebSocketMessage::getPayloadAsText)
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

  private fun broadcastIncomingMessages(): (FluxSink<Message>) -> Unit = { sink ->
    while (true) {
      try {
        // Turn external event source, `messages` into a reactive stream
        sink.next(messages.take())
      } catch (ex: InterruptedException) {
        Thread.currentThread().interrupt()
        throw RuntimeException(ex)
      }
    }
  }

  private fun jsonToMessage(json: String): Message {
    return try {
      objectMapper.readValue(json, Message::class.java)
    } catch (ex: Exception) {
      throw IllegalArgumentException("Invalid message format", ex)
    }
  }

  private fun toMessageWithMetadata(clientID: String, `when`: Instant): (Message) -> Message = {
    msg -> msg.copy(clientID, `when` = `when`)
  }
}
