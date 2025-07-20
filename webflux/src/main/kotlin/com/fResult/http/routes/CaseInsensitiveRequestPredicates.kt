package com.fResult.http.routes

import org.springframework.web.reactive.function.server.RequestPredicate
import org.springframework.web.reactive.function.server.ServerRequest

class CaseInsensitiveRequestPredicates(private val target: RequestPredicate) : RequestPredicate {
  override fun test(request: ServerRequest): Boolean {
    return target.test(LowercaseUriServerRequestWrapper(request))
  }
}
