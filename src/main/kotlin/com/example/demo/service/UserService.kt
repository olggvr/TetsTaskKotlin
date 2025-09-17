package com.example.demo.service

import com.example.demo.api.CreateUserRequest
import com.example.demo.api.UserResponse
import com.example.demo.domain.User
import com.example.demo.domain.UserRepository
import org.slf4j.LoggerFactory
import org.springframework.context.ApplicationEventPublisher
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UserService(
    private val repo: UserRepository,
    private val appEvents: ApplicationEventPublisher
) {
    private val log = LoggerFactory.getLogger(javaClass)

    @Transactional
    fun register(req: CreateUserRequest): UserResponse {
        val email = req.email?.trim().orEmpty()
        if (email.isEmpty()) throw IllegalArgumentException("Email must not be empty")
        if (repo.existsByEmail(email)) throw IllegalStateException("User with email already exists")

        val saved = repo.save(User(email = email))
        log.info("User registered: id={}, email={}", saved.id, saved.email)

        appEvents.publishEvent(UserCreatedEvent(saved.email))  // RabbitMQ позже, после коммита

        return UserResponse(saved.id, saved.email, saved.createdAt)
    }
}
