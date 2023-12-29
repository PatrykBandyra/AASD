package com.example.api.dto.response

data class SignInResponse(
        val token: String,
        val expiresAt: Long,
)
