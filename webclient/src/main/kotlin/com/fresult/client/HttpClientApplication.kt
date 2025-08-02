package com.fresult.client

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class HttpClientApplication

fun main(args: Array<String>) {
  runApplication<HttpClientApplication>(*args)
}
