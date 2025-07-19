package com.fResult.http.customers

import org.reactivestreams.Publisher
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import reactor.kotlin.core.publisher.toMono

@Component
class CustomerHandler(private val repository: CustomerRepository) {
  fun handleFindAll(ignored: ServerRequest): Mono<ServerResponse> =
    repository.findAll()
      .collectList()
      .flatMap(::listToOkResponse)
}

private inline fun <reified T> listToOkResponse(responseBody: List<T>): Mono<ServerResponse> =
  responseBody.toMono()
    .flatMapMany(Flux<T>::fromIterable)
    .let(::respondWithOkStreamBody)

private inline fun <reified T> respondWithOkStreamBody(responseBody: Publisher<T>): Mono<ServerResponse> =
  ServerResponse.ok().body(responseBody, T::class.java)
