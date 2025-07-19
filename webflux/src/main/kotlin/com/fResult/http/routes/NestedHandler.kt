package com.fResult.http.routes

import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import reactor.core.publisher.Mono
import reactor.kotlin.core.publisher.toMono

@Component
class NestedHandler {
  fun pathVariable(request: ServerRequest): Mono<ServerResponse> {
    return request.pathVariable("pv").toMono()
      .map(::greet)
      .flatMap { ServerResponse.ok().bodyValue(it) }
  }

  private fun greet(name: String?): Map<String, String> =
    mapOf("message" to "Hello, ${name ?: "World"}!")
}
