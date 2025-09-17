package com.example.demo.mq

import org.slf4j.LoggerFactory
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.stereotype.Component

@Component
class UserRegisteredListener {
    private val log = LoggerFactory.getLogger(javaClass)

    @RabbitListener(queues = ["\${app.rabbit.queue}"])
    fun onUserRegistered(event: UserRegisteredEvent) {
        log.info("Send welcome email to {}", event.email)
    }
}
