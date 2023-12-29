package com.example.api.config

import com.nimbusds.jose.jwk.JWKSelector
import com.nimbusds.jose.jwk.JWKSet
import com.nimbusds.jose.jwk.RSAKey
import com.nimbusds.jose.jwk.source.JWKSource
import com.nimbusds.jose.proc.SecurityContext
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.ProviderManager
import org.springframework.security.authentication.dao.DaoAuthenticationProvider
import org.springframework.security.config.Customizer
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy.STATELESS
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.factory.PasswordEncoderFactories
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.oauth2.jwt.JwtDecoder
import org.springframework.security.oauth2.jwt.JwtEncoder
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder
import org.springframework.security.web.SecurityFilterChain

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true)
internal class SecurityConfig(
        @Value("\${sigma.root-url}") private val rootUrl: String
) {

    private var rsaKey: RSAKey? = null

    @Bean
    internal fun authManager(userDetailsService: UserDetailsService): AuthenticationManager {
        val authProvider = DaoAuthenticationProvider()
        authProvider.setUserDetailsService(userDetailsService)
        authProvider.setPasswordEncoder(passwordEncoder())
        return ProviderManager(authProvider)
    }

    @Bean
    internal fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        return http
                .csrf { csrf -> csrf.disable() }
                .authorizeHttpRequests { auth ->
                    auth
                            .requestMatchers("${rootUrl}/auth/sign-in").permitAll()
                            .requestMatchers("${rootUrl}/auth/sign-up").permitAll()
                            .anyRequest().authenticated()
                }
                .sessionManagement { session -> session.sessionCreationPolicy(STATELESS) }
                .oauth2ResourceServer { oauth -> oauth.jwt(Customizer.withDefaults()) }
                .build()
    }

    @Bean
    internal fun jwkSource(): JWKSource<SecurityContext?>? {
        rsaKey = Jwks.generateRsa()
        val jwkSet = JWKSet(rsaKey)
        return JWKSource { jwkSelector: JWKSelector, _: SecurityContext? ->
            jwkSelector.select(jwkSet)
        }
    }

    @Bean
    internal fun jwtEncoder(jwks: JWKSource<SecurityContext?>?): JwtEncoder {
        return NimbusJwtEncoder(jwks)
    }

    @Bean
    internal fun jwtDecoder(): JwtDecoder {
        return NimbusJwtDecoder.withPublicKey(rsaKey?.toRSAPublicKey()).build()
    }

    @Bean
    internal fun passwordEncoder(): PasswordEncoder {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder()
    }
}