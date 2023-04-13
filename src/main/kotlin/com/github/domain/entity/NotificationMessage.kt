package com.github.domain.entity


data class NotificationMessage(
    val content: String,
    val messageType: MessageType,
)

enum class MessageType(name: String) {
    DISCORD("discord"),
    TELEGRAM("telegram")
}
