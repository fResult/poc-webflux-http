package com.fresult.client

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "client")
data class ClientProperties(val http: Http = Http()) {
  data class Http(
    val basic: Basic = Basic(),
    val rootUrl: String = "localhost:8080",
  ) {
    data class Basic(
      var username: String = "MyUser",
      var password: String = "MyPassword",
    )
  }
}
