package com.fResult.http.routes

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.MediaType
import org.springframework.web.reactive.function.server.RequestPredicates
import org.springframework.web.reactive.function.server.RouterFunction
import org.springframework.web.reactive.function.server.RouterFunctions
import org.springframework.web.reactive.function.server.ServerResponse
import java.nio.charset.Charset

@Configuration
class NestedFunctionalEndpointConfiguration {
  @Bean
  fun nested(nestedHandler: NestedHandler): RouterFunction<ServerResponse> {
    val jsonRP = RequestPredicates.accept(MediaType.APPLICATION_JSON)
      .or(RequestPredicates.accept(MediaType(MediaType.APPLICATION_JSON, Charset.forName("UTF-8"))))

    return RouterFunctions.route()
      .nest(RequestPredicates.path("/nested")) { builder ->
        builder.nest(jsonRP) { nestedBuilder ->
          nestedBuilder.GET("/{pv}", nestedHandler::pathVariable)
          nestedBuilder.GET("", nestedHandler::noPathVariable)
        }
      }.build()
  }
}
