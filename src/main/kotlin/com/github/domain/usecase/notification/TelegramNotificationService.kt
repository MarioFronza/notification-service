package com.github.domain.usecase.notification

import com.github.domain.entity.NotificationMessage
import com.github.domain.usecase.integration.TelegramIntegration

/**
 * This is a class that provides a way to send Telegram notifications by using a TelegramIntegration instance.
 * The class has a single method called "sendNotification" that takes a NotificationMessage object and
 * sends it to Telegram by calling the "sendMessage" method of the telegramIntegration instance. By encapsulating
 * the integration logic in this class, we can easily change the integration method used by simply swapping out
 * the telegramIntegration instance. This makes the code more modular and flexible. The constructor takes a single
 * parameter of type TelegramIntegration, which is used to send the message to Telegram.
 */
class TelegramNotificationService(
    private val telegramIntegration: TelegramIntegration
) {
    fun sendNotification(notificationMessage: NotificationMessage) {
        telegramIntegration.sendMessage(notificationMessage)
    }
}
