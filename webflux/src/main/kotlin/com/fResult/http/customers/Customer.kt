package com.fResult.http.customers

data class Customer(val id: String?, val name: String) {
  constructor(name: String) : this(null, name)
}
