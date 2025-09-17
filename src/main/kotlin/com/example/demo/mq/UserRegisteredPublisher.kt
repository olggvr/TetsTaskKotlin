package com.example.demo.mq

import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.stereotype.Component

data class UserRegisteredEvent(val email: String)

@Component
class UserRegisteredPublisher(
    private val rabbitTemplate: RabbitTemplate,
    private val props: RabbitProps
) {
    fun publish(email: String) {
        rabbitTemplate.convertAndSend(props.exchange, props.routingKey, UserRegisteredEvent(email))
    }
}
