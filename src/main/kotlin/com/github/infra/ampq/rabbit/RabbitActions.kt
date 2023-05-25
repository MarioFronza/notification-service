package com.github.infra.ampq.rabbit

import com.github.infra.http.ktor.plugins.custom.RabbitMQ
import com.github.infra.serialization.deserialize
import io.ktor.server.application.*


// Extension function to register a RabbitMQ consumer
fun Application.rabbitConsumer(configuration: RabbitMQInstance.() -> Unit): RabbitMQInstance =
    plugin(RabbitMQ).apply(configuration)

// Extension function to publish a message to a RabbitMQ exchange
fun ApplicationCall.publish(exchange: String, routingKey: String, body: String) {
    application.attributes[RabbitMQ.RabbitMQKey].publish(exchange, routingKey, body)
}

// Extension function to publish a message to a RabbitMQ exchange using a specific instance
fun RabbitMQInstance.publish(exchange: String, routingKey: String, body: String) {
    // Creates a channel and sends the message
    withChannel {
        basicPublish(exchange, routingKey, null, body.toByteArray())
    }
}

// Extension function to consume messages from a RabbitMQ queue
inline fun <reified T> RabbitMQInstance.consume(
    queue: String,
    crossinline rabbitDeliverCallback: (body: T) -> Unit,
) {
    // Creates a channel and starts consuming messages from the specified queue
    withChannel {
        basicConsume(
            queue,
            true,
            { _, message ->
                // Converts the message body to the specified type and invokes the callback
                runCatching {
                    val mappedEntity = message.body.decodeToString().deserialize<T>()
                    rabbitDeliverCallback.invoke(mappedEntity)
                }
            },
            { consumerTag ->
                println("Consume cancelled: (consumerTag = $consumerTag)")
            }
        )
    }
}


