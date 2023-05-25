package com.github

import com.github.infra.http.ktor.plugins.configureAmpq
import com.github.infra.http.ktor.plugins.configureDI
import com.github.infra.http.ktor.plugins.configureRouting
import com.github.infra.http.ktor.plugins.configureSerialization
import io.ktor.server.application.*
import io.ktor.server.netty.*

// This is the main function which is the entry point for the Kotlin program
fun main(args: Array<String>) = EngineMain.main(args)

// This is a function that defines the module of the Ktor application
// It configures various aspects of the application such as dependency injection, serialization, routing, and AMQP
@Suppress("unused")
fun Application.module() {
    configureDI() // Configures dependency injection
    configureSerialization() // Configures serialization
    configureRouting() // Configures routing
    configureAmpq() // Configures AMQP (Advanced Message Queuing Protocol)
}
