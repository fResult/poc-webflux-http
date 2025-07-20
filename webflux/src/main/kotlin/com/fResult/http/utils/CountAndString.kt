package com.fResult.http.utils

data class CountAndString(val count: Int, val message: String) {
  constructor(count: Int) : this(count, "#$count")
}
