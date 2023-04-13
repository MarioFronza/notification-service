package com.github.infra.http.ktor.controller.notification

import com.github.infra.ampq.rabbit.publish
import com.github.infra.http.ktor.presentation.CreateNotificationRequest
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.locations.*
import io.ktor.server.routing.*
import io.ktor.server.locations.post
import io.ktor.server.request.*
import io.ktor.server.response.*
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

@OptIn(KtorExperimentalLocationsAPI::class)
@Location("/notifications")
class NotificationController

fun Route.createNotification() = post<NotificationController> {
    val request = call.receive<CreateNotificationRequest>()
    val message = Json.encodeToString(request)
    val routingKey = request.messageType.lowercase()
    call.publish(exchange = "notification-requests", routingKey = routingKey, body = message)
    call.respond(HttpStatusCode.Created, request)
}