package com.example.api.service

import com.example.api.dto.response.SignInResponse
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.core.Authentication
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.oauth2.jwt.JwtClaimsSet
import org.springframework.security.oauth2.jwt.JwtEncoder
import org.springframework.security.oauth2.jwt.JwtEncoderParameters
import org.springframework.stereotype.Service
import java.time.Instant

@Service
class JwtGeneratorService(
        private val encoder: JwtEncoder,
        @Value("\${sigma.jwt.expiration-in-seconds}") private val jwtExpirationInSeconds: Long
) {

    companion object {
        private const val ISSUER = "self"
        private const val SCOPE_CLAIM_NAME = "scope"
    }

    fun generateToken(authentication: Authentication): SignInResponse {
        val now: Instant = Instant.now()
        val scope: Set<String> = authentication.authorities
                .map(GrantedAuthority::getAuthority)
                .toSet()
        val expiresAt = now.plusSeconds(jwtExpirationInSeconds)
        val claims: JwtClaimsSet = JwtClaimsSet.builder()
                .issuer(ISSUER)
                .issuedAt(now)
                .expiresAt(expiresAt)
                .subject(authentication.name)
                .claim(SCOPE_CLAIM_NAME, scope)
                .build()
        return SignInResponse(encoder.encode(JwtEncoderParameters.from(claims)).tokenValue, expiresAt.epochSecond)
    }
}