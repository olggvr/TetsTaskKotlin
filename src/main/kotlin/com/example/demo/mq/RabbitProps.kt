package com.example.demo.mq

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "app.rabbit")
data class RabbitProps(
    var queue: String = "user_registered",
    var exchange: String = "users.exchange",
    var routingKey: String = "user.registered",
    var bindingKeyPattern: String = "user.*"
)
