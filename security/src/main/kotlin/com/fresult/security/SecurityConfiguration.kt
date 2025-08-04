package com.fresult.security

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.core.userdetails.MapReactiveUserDetailsService
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails

@Configuration
class SecurityConfiguration {
  @Bean
  fun authentication(): MapReactiveUserDetailsService {
    val user1 = buildUserDetails("fResult", "password1", "USER")
    val user2 = buildUserDetails("KornZilla", "password2", "USER", "ADMIN")

    return MapReactiveUserDetailsService(user1, user2)
  }

  private fun buildUserDetails(
    username: String,
    password: String,
    vararg roles: String,
  ): UserDetails =
    User.withUsername(username)
      .password(password)
      .roles(*roles)
      .build()
}
