package com.fResult.http.routes

import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.server.HandlerFunction
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import reactor.kotlin.core.publisher.toMono

@Configuration
class CustomRoutePredicates {
  val handler: HandlerFunction<ServerResponse> = HandlerFunction { request: ServerRequest ->
    request.queryParam("name")
      .toMono()
      .map { "Hello, ${it.orElse("World")}" }
      .flatMap { ServerResponse.ok().bodyValue(it) }
  }
}
