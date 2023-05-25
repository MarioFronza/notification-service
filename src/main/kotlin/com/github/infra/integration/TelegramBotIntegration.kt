package com.github.infra.integration

import com.github.domain.entity.NotificationMessage
import com.github.domain.usecase.integration.TelegramIntegration
import com.pengrad.telegrambot.TelegramBot
import com.pengrad.telegrambot.request.SendMessage
import com.typesafe.config.ConfigFactory


class TelegramBotIntegration(
    private val token: String = ConfigFactory.load().getString("telegram.token"), // Initializes the token value from the configuration file
    private val chatId: String = ConfigFactory.load().getString("telegram.chat_id") // Initializes the chatId value from the configuration file
) : TelegramIntegration { // Defines a class that implements the TelegramIntegration interface

    private val bot: TelegramBot = TelegramBot(token) // Initializes the TelegramBot instance with the token value
    override fun sendMessage(message: NotificationMessage) { // Implements the sendMessage method from the TelegramIntegration interface
        bot.execute(SendMessage(chatId, message.content)) // Sends a message to the specified chat using the TelegramBot instance
    }

}
