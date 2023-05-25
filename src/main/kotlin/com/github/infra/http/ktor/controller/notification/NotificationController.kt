package com.github.infra.http.ktor.controller.notification

import com.github.infra.ampq.rabbit.publish
import com.github.infra.http.ktor.presentation.CreateNotificationRequest
import com.github.infra.serialization.serializeToString
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.locations.*
import io.ktor.server.routing.*
import io.ktor.server.locations.post
import io.ktor.server.request.*
import io.ktor.server.response.*
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

/**
 * This is a Ktor route that handles incoming HTTP POST requests to create a notification.
 * The route is associated with the "/notifications" path using the @Location annotation.
 */
@Location("/notifications")
class NotificationController

/**
 * This is an extension function for the Ktor Route class that creates a new route to handle HTTP POST requests
 * to create a notification. The function is associated with the NotificationController class using the generic
 * type parameter in the post method call.
 */
fun Route.createNotification() = post<NotificationController> {

    /**
     * This line retrieves the request body from the HTTP POST request and deserializes it into a
     * CreateNotificationRequest object using the call.receive method.
     */
    val request = call.receive<CreateNotificationRequest>()

    /**
     * This line retrieves the routing key for the notification from the metadata field of the
     * CreateNotificationRequest object.
     */
    val routingKey = request.metadata.routingKey

    /**
     * This line serializes the CreateNotificationRequest object into a JSON string using the
     * serializeToString extension function defined earlier.
     */
    val message = request.serializeToString()

    /**
     * This line publishes the serialized message to an AMQP exchange using the call.publish method,
     * specifying the exchange name, routing key, and message body as parameters.
     */
    call.publish(exchange = "notification-requests", routingKey = routingKey, body = message)

    /**
     * This line sends an HTTP 201 Created response back to the client with the original request body as
     * the response body.
     */
    call.respond(HttpStatusCode.Created, request)
}
