package com.github.infra.http.ktor.presentation

import kotlinx.serialization.Serializable

@Serializable
data class CreateNotificationRequest(
    val content: String,
    val messageType: String
)