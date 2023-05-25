package com.github.infra.http.ktor.plugins

import com.github.domain.usecase.notification.DiscordNotificationService
import com.github.domain.usecase.notification.TelegramNotificationService
import com.github.infra.ampq.rabbit.consume
import com.github.infra.ampq.rabbit.rabbitConsumer
import com.github.infra.http.ktor.plugins.custom.RabbitMQ
import com.github.infra.http.ktor.presentation.CreateNotificationRequest
import io.ktor.server.application.*
import kotlinx.coroutines.launch
import org.koin.ktor.ext.inject


fun Application.configureAmpq() = environment.config.run {
    val discordNotificationService by inject<DiscordNotificationService>()
    val telegramNotificationService by inject<TelegramNotificationService>()

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
        consume<CreateNotificationRequest>("discord-notifications") { body ->
            launch {
                discordNotificationService.sendNotification(body.toEntity())
            }
        }

        consume<CreateNotificationRequest>("telegram-notifications") { body ->
            launch {
                telegramNotificationService.sendNotification(body.toEntity())
            }
        }
    }

}

