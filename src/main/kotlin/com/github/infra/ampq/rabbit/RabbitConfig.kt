package com.github.infra.ampq.rabbit

import com.rabbitmq.client.Channel

class RabbitConfig private constructor() {

    var uri: String? = null
    var connectionName: String? = null
    var rabbitMQInstance: RabbitMQInstance? = null

    internal lateinit var initializeBlock: (Channel.() -> Unit)

    fun initialize(block: (Channel.() -> Unit)) {
        initializeBlock = block
    }

    companion object {
        fun create(): RabbitConfig {
            return RabbitConfig()
        }
    }

}