package com.github.infra.ampq.rabbit

import com.github.infra.http.ktor.plugins.custom.RabbitMQ
import com.rabbitmq.client.AMQP
import com.rabbitmq.client.Channel
import com.rabbitmq.client.Delivery
import io.ktor.server.application.*
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

fun Application.rabbitConsumer(configuration: RabbitMQInstance.() -> Unit): RabbitMQInstance =
    plugin(RabbitMQ).apply(configuration)

fun RabbitMQInstance.publish(exchange: String, routingKey: String, props: AMQP.BasicProperties? = null, body: String) {
    withChannel {
        basicPublish(exchange, routingKey, props, body.toByteArray())
    }
}

fun ApplicationCall.publish(exchange: String, routingKey: String, props: AMQP.BasicProperties? = null, body: String) {
    application.attributes[RabbitMQ.RabbitMQKey].publish(exchange, routingKey, props, body)
}

inline fun <reified T> RabbitMQInstance.consume(
    queue: String,
    autoAck: Boolean = true,
    crossinline rabbitDeliverCallback: ConsumerScope.(body: T) -> Unit,
) {
    withChannel {
        basicConsume(
            queue,
            autoAck,
            { consumerTag, message ->
                runCatching {
                    val mappedEntity = Json.decodeFromString<T>(message.body.decodeToString())

                    val scope = ConsumerScope(
                        channel = this,
                        message = message
                    )

                    rabbitDeliverCallback.invoke(scope, mappedEntity)
                }.getOrElse {
                    println(
                        "DeliverCallback error: (" +
                                "messageId = ${message.properties.messageId}, " +
                                "consumerTag = $consumerTag)",
                    )
                }
            },
            { consumerTag ->
                println("Consume cancelled: (consumerTag = $consumerTag)")
            }
        )
    }
}

class ConsumerScope(
    private val channel: Channel,
    private val message: Delivery,
) {

    fun ack(multiple: Boolean = false) {
        channel.basicAck(message.envelope.deliveryTag, multiple)
    }

    fun nack(multiple: Boolean = false, requeue: Boolean = false) {
        channel.basicNack(message.envelope.deliveryTag, multiple, requeue)
    }

    fun reject(requeue: Boolean = false) {
        channel.basicReject(message.envelope.deliveryTag, requeue)
    }
}
