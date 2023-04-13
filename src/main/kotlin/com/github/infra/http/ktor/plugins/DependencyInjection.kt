package com.github.infra.http.ktor.plugins

import com.github.domain.adapter.logger.ApplicationLogger
import com.github.domain.integration.NotificationSenderIntegration
import com.github.domain.usecase.notification.NotificationService
import com.github.infra.integration.DiscordNotificationSender
import com.github.infra.integration.TelegramNotificationSender
import com.github.infra.logger.slf4j.LoggerSlf4j
import io.ktor.server.application.*
import org.koin.core.qualifier.named
import org.koin.dsl.module
import org.koin.ktor.plugin.Koin

fun Application.configureDI() {
    install(Koin) {
        modules(appModule, notificationModule)
    }
}

val notificationModule = module {
    single<NotificationSenderIntegration>(named("discord")) { DiscordNotificationSender(get()) }
    single<NotificationSenderIntegration>(named("telegram")) { TelegramNotificationSender(get()) }

    factory { (type: String) ->
        when (type) {
            "discord" -> get<NotificationSenderIntegration>(named("discord"))
            "telegram" -> get<NotificationSenderIntegration>(named("telegram"))
            else -> throw IllegalArgumentException("Invalid notification type")
        }
    }
}

val appModule = module {
    single { NotificationService(get()) }
    single<ApplicationLogger> { LoggerSlf4j() }

    factory { (type: String) -> get<NotificationService>().senderFactory(type) }
}
