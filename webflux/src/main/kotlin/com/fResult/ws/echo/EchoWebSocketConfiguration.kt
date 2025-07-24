package com.fResult.ws.echo

import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import org.springframework.context.annotation.Configuration

@Configuration
class EchoWebSocketConfiguration {
  companion object {
    private val log: Logger = LogManager.getLogger(EchoWebSocketConfiguration::class.java)
  }
}
