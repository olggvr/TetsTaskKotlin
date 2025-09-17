package com.example.demo.mq

import org.springframework.amqp.core.*
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

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
            .with(props.bindingKeyPattern)
}
