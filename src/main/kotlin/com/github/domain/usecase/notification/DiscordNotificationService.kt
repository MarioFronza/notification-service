package com.github.domain.usecase.notification

import com.github.domain.entity.NotificationMessage
import com.github.domain.usecase.integration.DiscordIntegration

/**
 * This is a class that provides a way to send Discord notifications by using a DiscordIntegration instance.
 * The class has a single method called "sendNotification" that takes a NotificationMessage object and
 * sends it to Discord by calling the "sendMessage" method of the discordIntegration instance. By encapsulating
 * the integration logic in this class, we can easily change the integration method used by simply swapping out
 * the discordIntegration instance. This makes the code more modular and flexible. The constructor takes a single
 * parameter of type DiscordIntegration, which is used to send the message to Discord.
 */
class DiscordNotificationService(
    private val discordIntegration: DiscordIntegration
) {
    fun sendNotification(notificationMessage: NotificationMessage) {
        discordIntegration.sendMessage(message = notificationMessage)
    }
}
