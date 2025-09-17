package com.example.demo.api

import java.time.Instant
import java.util.*

data class CreateUserRequest(val email: String)
data class UserResponse(val id: UUID, val email: String, val createdAt: Instant)
