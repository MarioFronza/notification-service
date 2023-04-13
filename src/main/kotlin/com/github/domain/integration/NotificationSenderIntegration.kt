package com.github.domain.integration

import com.github.domain.entity.NotificationMessage

interface NotificationSenderIntegration {

    suspend fun sendNotification(notificationMessage: NotificationMessage)
}