package com.github.domain.usecase.notification

import com.github.domain.entity.NotificationMessage
import com.github.domain.integration.NotificationSenderIntegration

class NotificationService(
    val senderFactory: (String) -> NotificationSenderIntegration
) {

    suspend fun sendNotification(notificationMessage: NotificationMessage) {
        val sender = senderFactory(notificationMessage.messageType.name)
        sender.sendNotification(notificationMessage)
    }

}