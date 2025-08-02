package com.fresult.servlets

import kotlinx.coroutines.reactor.awaitSingle
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.server.RouterFunction
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.coRouter

@Configuration
class GreetingsRouteConfiguration {
  @Bean
  fun routes(): RouterFunction<ServerResponse> = coRouter {
    GET("/hello/functional/{name}", ::helloFunctional)
  }
}

suspend fun helloFunctional(request: ServerRequest): ServerResponse {
  val name = request.pathVariable("name")

  return Greetings.greet("functional", name)
    .flatMap(ServerResponse.ok()::bodyValue).awaitSingle()
}
