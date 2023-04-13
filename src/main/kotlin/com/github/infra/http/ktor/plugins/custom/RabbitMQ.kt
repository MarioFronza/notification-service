package com.github.infra.http.ktor.plugins.custom

import com.github.infra.ampq.rabbit.RabbitConfig
import com.github.infra.ampq.rabbit.RabbitMQInstance
import io.ktor.server.application.*
import io.ktor.util.*

class RabbitMQ {

    companion object Feature : BaseApplicationPlugin<Application, RabbitConfig, RabbitMQInstance> {

        val RabbitMQKey = AttributeKey<RabbitMQInstance>("RabbitMQ")
        override val key = RabbitMQKey

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