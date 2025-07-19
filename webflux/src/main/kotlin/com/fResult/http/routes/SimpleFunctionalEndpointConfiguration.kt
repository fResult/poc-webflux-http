package com.fResult.http.routes

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.server.RouterFunction
import org.springframework.web.reactive.function.server.RouterFunctions
import org.springframework.web.reactive.function.server.ServerResponse

@Configuration
class SimpleFunctionalEndpointConfiguration {
  @Bean
  fun simple(): RouterFunction<ServerResponse> =
    RouterFunctions.route()
      .GET("/hello/{name}") { request ->
        val name = request.pathVariable("name")
        val message = "Hello, $name!"
        ServerResponse.ok().bodyValue(message)
      }.build()
}
