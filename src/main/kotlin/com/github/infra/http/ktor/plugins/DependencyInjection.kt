package com.github.infra.http.ktor.plugins

import com.github.domain.usecase.integration.DiscordIntegration
import com.github.domain.usecase.integration.TelegramIntegration
import com.github.domain.usecase.notification.DiscordNotificationService
import com.github.domain.usecase.notification.TelegramNotificationService
import com.github.infra.integration.JDADiscordIntegration
import com.github.infra.integration.TelegramBotIntegration
import io.ktor.server.application.*
import org.koin.dsl.module
import org.koin.ktor.plugin.Koin

fun Application.configureDI() {
    install(Koin) {
        modules(appModule)
    }
}

val appModule = module {

    single<DiscordIntegration> { JDADiscordIntegration() }
    single<TelegramIntegration> { TelegramBotIntegration() }

    single { TelegramNotificationService(get()) }
    single { DiscordNotificationService(get()) }
}

