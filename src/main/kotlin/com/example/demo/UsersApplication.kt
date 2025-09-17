package com.example.demo

import com.example.demo.mq.RabbitProps
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication

@SpringBootApplication
@EnableConfigurationProperties(RabbitProps::class)
class UsersApplication

fun main(args: Array<String>) {
    runApplication<UsersApplication>(*args)
}
