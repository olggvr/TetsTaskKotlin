package com.example.demo.api

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import java.time.Instant
import java.util.UUID

data class CreateUserRequest(
    @field:NotBlank(message = "Email must not be empty")
    @field:Email(message = "Invalid email format")
    val email: String?
)

data class UserResponse(
    val id: UUID,
    val email: String,
    val createdAt: Instant
)
