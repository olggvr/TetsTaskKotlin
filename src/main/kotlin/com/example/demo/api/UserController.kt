package com.example.demo.api

import com.example.demo.service.UserService
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.net.URI

@RestController
@RequestMapping("/users")
class UserController(
    private val service: UserService
) {
    @PostMapping
    fun create(@Valid @RequestBody req: CreateUserRequest): ResponseEntity<UserResponse> {
        val user = service.register(req)
        val uri = URI.create("/users/${user.id}")
        return ResponseEntity.created(uri).body(user)
    }
}
