package com.example.demo.service

import com.example.demo.api.CreateUserRequest
import com.example.demo.api.UserResponse
import com.example.demo.domain.User
import com.example.demo.domain.UserRepository
import com.example.demo.mq.UserRegisteredPublisher
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UserService(
    private val repo: UserRepository,
    private val publisher: UserRegisteredPublisher
) {
    @Transactional
    fun register(req: CreateUserRequest): UserResponse {
        require(req.email.isNotBlank()) { "email must not be blank" }
        if (repo.existsByEmail(req.email)) {
            throw IllegalStateException("User with email already exists")
        }
        val saved = repo.save(User(email = req.email.trim()))
        publisher.publish(saved.email)
        return UserResponse(saved.id, saved.email, saved.createdAt)
    }
}
