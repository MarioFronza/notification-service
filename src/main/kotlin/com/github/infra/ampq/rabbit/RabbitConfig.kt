package com.github.infra.ampq.rabbit

import com.rabbitmq.client.Channel
import kotlin.reflect.KClass

// Define a class called RabbitConfig with a private constructor
class RabbitConfig private constructor() {

    // Declare some properties for the RabbitConfig class
    var uri: String? = null
    var connectionName: String? = null
    var rabbitMQInstance: RabbitMQInstance? = null

    // Define some function types for the RabbitConfig class
    internal lateinit var initializeBlock: (Channel.() -> Unit)

    // Define a function to initialize the RabbitMQ connection
    fun initialize(block: (Channel.() -> Unit)) {
        initializeBlock = block
    }

    // Define a companion object to create new instances of RabbitConfig
    companion object {
        fun create(): RabbitConfig {
            return RabbitConfig()
        }
    }

}
