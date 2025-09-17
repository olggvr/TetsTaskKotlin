package com.example.demo.mq

import org.springframework.amqp.core.Binding
import org.springframework.amqp.core.BindingBuilder
import org.springframework.amqp.core.Queue
import org.springframework.amqp.core.TopicExchange
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@ConfigurationProperties(prefix = "app.rabbit")
data class RabbitProps(
    var queue: String = "user_registered",
    var exchange: String = "users.exchange",
    var routingKey: String = "user_registered"
)

@Configuration
class RabbitConfig {

    @Bean
    fun usersExchange(props: RabbitProps) = TopicExchange(props.exchange, true, false)

    @Bean
    fun userRegisteredQueue(props: RabbitProps) = Queue(props.queue, true)

    @Bean
    fun binding(props: RabbitProps): Binding =
        BindingBuilder.bind(userRegisteredQueue(props))
            .to(usersExchange(props))
            .with(props.routingKey)
}
