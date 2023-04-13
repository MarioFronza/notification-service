package com.github.infra.http.ktor.plugins

import com.github.domain.entity.NotificationMessage
import com.github.domain.usecase.notification.NotificationService
import com.github.infra.ampq.rabbit.consume
import com.github.infra.ampq.rabbit.rabbitConsumer
import com.github.infra.http.ktor.plugins.custom.RabbitMQ
import io.ktor.server.application.*
import kotlinx.coroutines.launch
import org.koin.ktor.ext.inject

fun Application.configureAmpq() = environment.config.run {
    val notificationService by inject<NotificationService>()

    install(RabbitMQ) {
        uri = property("ampq.url").getString()
        connectionName = "rabbitmq-connection"

        initialize {
            exchangeDeclare("notification-requests", "direct", true)
            queueDeclare(
                "discord-notifications",
                true,
                false,
                false,
                emptyMap()
            )
            queueDeclare(
                "telegram-notifications",
                true,
                false,
                false,
                emptyMap()
            )
            queueBind("discord-notifications", "notification-requests", "discord")
            queueBind("telegram-notifications", "notification-requests", "telegram")
        }
    }

    rabbitConsumer {
        consume<NotificationMessage>("discord-notifications") { body ->
            launch {
                notificationService.sendNotification(body)
            }
        }

        consume<NotificationMessage>("telegram-notifications") { body ->
            launch {
                notificationService.sendNotification(body)
            }
        }
    }

}