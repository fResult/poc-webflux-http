package com.fResult.http.customers

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Mono
import java.net.URI

@RestController
@RequestMapping("/rc/customers")
class CustomerRestController(private val repository: CustomerRepository) {
  @GetMapping
  fun all() = repository.findAll()

  @GetMapping("/{id}")
  fun byId(@PathVariable id: String) = repository.findById(id)

  @PostMapping
  fun create(@RequestBody customer: Customer): Mono<ResponseEntity<Customer>> = repository.save(customer)
    .map { ResponseEntity.created(URI.create("/rc/customers/${it.id}")).body(it) }
}
