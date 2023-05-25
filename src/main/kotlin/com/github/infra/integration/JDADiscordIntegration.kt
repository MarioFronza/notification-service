package com.github.infra.integration

import com.github.domain.entity.NotificationMessage
import com.github.domain.usecase.integration.DiscordIntegration
import com.typesafe.config.ConfigFactory
import net.dv8tion.jda.api.JDA
import net.dv8tion.jda.api.JDABuilder

/**
 * This class implements the DiscordIntegration interface and handles sending notifications to a Discord channel using JDA.
 * The token is loaded from the configuration file.
 */
class JDADiscordIntegration(
    private val token: String = ConfigFactory.load().getString("discord.token")
) : DiscordIntegration {

    /**
     * This property stores an instance of the JDA library for interfacing with the Discord API.
     */
    private val jda = createJDABuilder()

    /**
     * This function sends a message to a Discord channel.
     * It first retrieves a list of channels with the name "general" and sends the message to the first channel in the list.
     */
    override fun sendMessage(message: NotificationMessage) {
        val channels = jda.getTextChannelsByName(MAIN_CHANNEL_NAME, true)
        channels.first().sendMessage(message.content).queue()
    }

    /**
     * This private function creates an instance of the JDA library.
     * It uses the token property to authenticate with the Discord API.
     */
    private fun createJDABuilder(): JDA {
        val jda = JDABuilder.createDefault(token).build()
        return jda.awaitReady()
    }

    /**
     * This companion object stores a constant representing the name of the main channel on the Discord server.
     */
    companion object {
        const val MAIN_CHANNEL_NAME = "general"
    }
}
