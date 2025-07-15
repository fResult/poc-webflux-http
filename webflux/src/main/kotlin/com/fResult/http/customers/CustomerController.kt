package com.fResult.http.customers

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/customers")
class CustomerController(private val repository: CustomerRepository) {
  @GetMapping
  fun all() = repository.findAll()

  @GetMapping("/{id}")
  fun byId(@PathVariable id: String) = repository.findById(id)

  @PostMapping
  fun create(@RequestBody customer: Customer) = repository.save(customer)
}
