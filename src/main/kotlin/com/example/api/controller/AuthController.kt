package com.example.api.controller

import com.example.api.dto.request.SignInRequest
import com.example.api.dto.request.SignUpRequest
import com.example.api.dto.response.SignInResponse
import com.example.api.dto.response.SignUpResponse
import com.example.api.model.SigmaUser
import com.example.api.service.JwtGeneratorService
import com.example.api.service.SigmaUserService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("\${sigma.root-url}/auth")
internal class AuthController(
        private val jwtGeneratorService: JwtGeneratorService,
        private val authManager: AuthenticationManager,
        private val sigmaUserService: SigmaUserService
) {

    private val logger: Logger = LoggerFactory.getLogger(AuthController::class.java)

    @PostMapping("sign-in")
    fun signIn(@RequestBody signInRequest: SignInRequest): ResponseEntity<SignInResponse> {
        logger.info("Token requested for ${signInRequest.username}")
        val authentication: Authentication = authManager.authenticate(UsernamePasswordAuthenticationToken(
                signInRequest.username, signInRequest.password
        ))
        return ResponseEntity.ok(jwtGeneratorService.generateToken(authentication))
    }

    @PostMapping("sign-up")
    fun signUp(@RequestBody signUpRequest: SignUpRequest): ResponseEntity<SignUpResponse> {
        val savedUser: SigmaUser = sigmaUserService.registerUser(signUpRequest)
        return ResponseEntity.ok(SignUpResponse(savedUser.username))
    }
}