package com.example.api.dto.request

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Pattern
import jakarta.validation.constraints.Size

data class SignInRequest(

        @field:NotBlank(message = "Username cannot be blank")
        val username: String,

        @field:NotBlank(message = "Password cannot be blank")
        @field:Size(min = 8, max = 16, message = "Password must have length between 8 and 16")
        @field:Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@\$!%*?&])[A-Za-z\\d@\$!%*?&]{8,16}\$", message = "Password has invalid format")
        val password: String
)
