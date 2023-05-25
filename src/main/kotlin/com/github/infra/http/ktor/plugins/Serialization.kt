package com.github.infra.http.ktor.plugins

import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.response.*
import io.ktor.server.application.*
import io.ktor.server.routing.*

/**
 * This function is an extension function for the Ktor Application class that configures content negotiation
 * for the application.
 */
fun Application.configureSerialization() {

    /**
     * This line installs a content negotiation feature that allows the application to respond to requests
     * with JSON payloads.
     */
    install(ContentNegotiation) {

        /**
         * This line adds a JSON serializer to the content negotiation feature, which enables the application
         * to serialize Kotlin objects to JSON format.
         */
        json()
    }
}

