package com.github.infra.ampq.rabbit

import com.rabbitmq.client.Channel
import com.rabbitmq.client.ConnectionFactory

class RabbitMQInstance(
    private val config: RabbitConfig
) {

    private val connectionFactory = ConnectionFactory().apply {
        setUri(config.uri)
    }
    private val connection = connectionFactory.newConnection(config.connectionName)
    private val channel = connection.createChannel()

    fun initialize() {
        config.initializeBlock.invoke(channel)
    }

    fun withChannel(block: Channel.() -> Unit) {
        block.invoke(channel)
    }

}