package com.fResult.http.filters

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.server.RouterFunction
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.bodyValueAndAwait
import org.springframework.web.reactive.function.server.coRouter

@Configuration
class ErrorHandlingRouteConfiguration {
  @Bean
  fun errors(): RouterFunction<ServerResponse> = coRouter {
    GET("/errors").nest {
      GET("products/{id}", ::findProductById)
    }
  }.filter { request, next ->
    next.handle(request).onErrorResume(ProductNotFoundException::class.java) { ex ->
      ServerResponse.notFound().build()
    }
  }

  private suspend fun findProductById(request: ServerRequest): ServerResponse {
    val customerId = request.pathVariable("id")

    return if (setOf("1", "2").contains(customerId)) {
      throw ProductNotFoundException("Customer with ID [$customerId] not found")
    } else {
      ServerResponse.ok().bodyValueAndAwait(Product(customerId))
    }
  }
}

data class Product(val id: String)

class ProductNotFoundException : RuntimeException {
  constructor(message: String) : super(message)
  constructor(cause: Throwable) : super(cause)
  constructor(message: String, cause: Throwable) : super(message, cause)
}
