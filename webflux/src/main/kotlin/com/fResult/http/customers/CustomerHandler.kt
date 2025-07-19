package com.fResult.http.customers

import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import reactor.core.publisher.Mono

@Component
class CustomerHandler(private val repository: CustomerRepository) {
  fun handleFindAll(ignored: ServerRequest): Mono<ServerResponse> =
    repository.findAll()
      .collectList()
      .flatMap(::toResponseWithBody)
}

private inline fun <reified T> toResponseWithBody(responseBody: List<T>): Mono<ServerResponse> =
  ServerResponse.ok().body(responseBody, T::class.java)
