package com.github.infra.http.ktor.plugins.custom

import com.github.infra.ampq.rabbit.RabbitConfig
import com.github.infra.ampq.rabbit.RabbitMQInstance
import io.ktor.server.application.*
import io.ktor.util.*

class RabbitMQ {

    companion object Feature : BaseApplicationPlugin<Application, RabbitConfig, RabbitMQInstance> {

        // Key used to store and retrieve the RabbitMQ instance in the application attributes.
        val RabbitMQKey = AttributeKey<RabbitMQInstance>("RabbitMQ")
        override val key = RabbitMQKey

        // Installs the RabbitMQ feature in the pipeline and configures it with the provided configuration.
        // If no RabbitMQ instance is provided in the configuration, creates a new instance using RabbitConfig.create()
        // and initializes it. Stores the instance in the application attributes with the RabbitMQKey.
        override fun install(pipeline: Application, configure: RabbitConfig.() -> Unit): RabbitMQInstance {
            val config = RabbitConfig.create()
            config.apply(configure)

            val rabbit = config.rabbitMQInstance ?: RabbitMQInstance(config)
            rabbit.apply { initialize() }

            pipeline.attributes.put(key, rabbit)

            return rabbit
        }

    }
}
