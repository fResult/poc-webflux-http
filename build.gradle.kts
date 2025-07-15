plugins {
  kotlin("jvm") version "2.2.0"
  kotlin("plugin.spring") version "2.2.0"
  id("org.springframework.boot") version "4.0.0-SNAPSHOT"
  id("io.spring.dependency-management") version "1.1.7"
}

allprojects {
  group = "com.fResult"
  version = "0.0.1"

  repositories {
    mavenCentral()
    // Remove if Spring Boot 4.0.0 is released
    maven { url = uri("https://repo.spring.io/milestone") }
    maven { url = uri("https://repo.spring.io/snapshot") }
  }
}

subprojects {
  apply(plugin = "kotlin")
  apply(plugin = "org.springframework.boot")
  apply(plugin = "io.spring.dependency-management")

  dependencies {
    implementation("org.springframework.boot:spring-boot-starter-test")

    developmentOnly("org.springframework.boot:spring-boot-devtools")
  }

  tasks.withType<Test> {
    useJUnitPlatform()
  }
}
