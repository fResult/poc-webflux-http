package com.fResult.http.customers

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.server.RouterFunction
import org.springframework.web.reactive.function.server.RouterFunctions
import org.springframework.web.reactive.function.server.ServerResponse

@Configuration
class CustomerApiEndpointConfiguration {
  @Bean
  fun customerApis(handler: CustomerHandler): RouterFunction<ServerResponse> {
    return RouterFunctions.route()
      .GET("fe/customers", handler::handleFindAll)
      .build()
  }
}
