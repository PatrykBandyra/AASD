package com.example.api.dto.request

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Pattern
import jakarta.validation.constraints.Size

data class SignUpRequest(

        @field:NotBlank(message = "Username cannot be blank")
        val username: String,

        @field:NotBlank(message = "Password cannot be blank")
        @field:Size(min = 8, max = 16, message = "Password must have length between 8 and 16")
        @field:Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@\$!%*?&])[A-Za-z\\d@\$!%*?&]{8,16}\$", message = "Password has invalid format")
        val password: String,

        @field:NotBlank(message = "Email cannot be blank")
        @field:Email(message = "Email has invalid format")
        val email: String,

        @field:NotBlank(message = "First name cannot be blank")
        val firstName: String,

        @field:NotBlank(message = "Last name cannot be blank")
        val lastName: String
)
