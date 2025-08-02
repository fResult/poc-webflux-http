package com.fresult.client

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "client")
data class ClientProperties(val http: Http = Http()) {
  data class Http(
    val basic: Basic = Basic(),
    val rootUrl: String? = null,
  ) {
    data class Basic(
      var username: String? = null,
      var password: String? = null,
    )
  }
}
