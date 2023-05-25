package com.github.infra.http.ktor.presentation

import com.github.domain.entity.NotificationMessage
import kotlinx.serialization.Serializable

/**
 * This annotation indicates that the following data class is serializable to and from JSON using Ktor's
 * built-in serialization support.
 */
@Serializable
data class CreateNotificationRequest(
    /**
     * This property stores the content of the notification message as a string.
     */
    val content: String,

    /**
     * This property stores metadata associated with the notification message, such as a routing key.
     */
    val metadata: MetaData
) {

    /**
     * This function converts the CreateNotificationRequest object to a NotificationMessage object.
     */
    fun toEntity() = NotificationMessage(
        content = content
    )
}

/**
 * This annotation indicates that the following data class is serializable to and from JSON using Ktor's
 * built-in serialization support.
 */
@Serializable
data class MetaData(
    /**
     * This property stores the routing key associated with the notification message.
     */
    val routingKey: String
)
