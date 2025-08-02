package com.fresult.client

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication

@SpringBootApplication
@EnableConfigurationProperties(ClientProperties::class)
class HttpClientApplication

fun main(args: Array<String>) {
  runApplication<HttpClientApplication>(*args)
}
