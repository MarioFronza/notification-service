package com.github.infra.integration

import com.github.domain.adapter.logger.ApplicationLogger
import com.github.domain.entity.NotificationMessage
import com.github.domain.integration.NotificationSenderIntegration

class TelegramNotificationSender(
    private val applicationLogger: ApplicationLogger
) : NotificationSenderIntegration {
    override suspend fun sendNotification(notificationMessage: NotificationMessage) {
        applicationLogger.info("send ${notificationMessage.messageType.name} notification: ${notificationMessage.content}")
    }
}