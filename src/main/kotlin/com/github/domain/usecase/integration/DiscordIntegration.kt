package com.github.domain.usecase.integration

import com.github.domain.entity.NotificationMessage

/**
 * This is an interface that defines a method called "sendMessage" for sending a Discord message.
 * The method takes a single parameter of type NotificationMessage, which contains the content of the message.
 * Interfaces in Kotlin are used to define a contract that classes must follow. Any class that implements the
 * DiscordIntegration interface must provide an implementation of the sendMessage method, which allows it to send
 * messages to Discord. By using an interface, we can define a common set of methods that multiple classes can
 * implement, which allows us to write code that is more modular and extensible.
 */
interface DiscordIntegration {
    fun sendMessage(message: NotificationMessage)
}
