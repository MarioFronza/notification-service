@file:OptIn(KtorExperimentalLocationsAPI::class)

package com.github.infra.http.ktor.plugins

import com.github.infra.http.ktor.controller.notification.createNotification
import io.ktor.server.routing.*
import io.ktor.server.application.*
import io.ktor.server.locations.*

fun Application.configureRouting() {
    install(Locations)
    routing {
        createNotification()
    }
}
