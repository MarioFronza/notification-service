package com.github.infra.ampq.rabbit

import com.rabbitmq.client.Channel
import com.rabbitmq.client.ConnectionFactory

class RabbitMQInstance(
    val config: RabbitConfig  // Constructor that takes a RabbitConfig object as a parameter
) {

    // Creates a ConnectionFactory and sets its URI to the URI from the RabbitConfig object
    private val connectionFactory = ConnectionFactory().apply {
        setUri(config.uri)
    }
    // Creates a connection using the connection name from the RabbitConfig object
    private val connection = connectionFactory.newConnection(config.connectionName)
    // Creates a channel using the above connection
    private val channel = connection.createChannel()

    // Calls the initializeBlock function from the RabbitConfig object to initialize the channel
    fun initialize() {
        config.initializeBlock.invoke(channel)
    }

    // Allows a lambda function to be executed with the channel object
    fun withChannel(block: Channel.() -> Unit) {
        block.invoke(channel)
    }
}
