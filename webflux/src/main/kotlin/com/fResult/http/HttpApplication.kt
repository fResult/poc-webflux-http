package com.fResult.http

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@SpringBootApplication
class HttpApplication

fun main(args: Array<String>) {
  runApplication<HttpApplication>(*args)
}

@RestController
@RequestMapping("/greets")
class GreetController {

  @GetMapping("/{name}")
  fun greet(@PathVariable name: String): String {
    return "Hello, $name!"
  }
}
