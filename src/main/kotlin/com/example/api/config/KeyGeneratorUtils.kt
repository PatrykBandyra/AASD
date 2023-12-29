package com.example.api.config

import org.springframework.stereotype.Component
import java.security.KeyPair
import java.security.KeyPairGenerator

@Component
internal class KeyGeneratorUtils {
    internal companion object {
        private const val ALGORITHM: String = "RSA"
        private const val KEY_SIZE: Int = 2048

        internal fun generateRsaKey(): KeyPair? {
            val keyPair: KeyPair?
            try {
                val keyPairGenerator = KeyPairGenerator.getInstance(ALGORITHM)
                keyPairGenerator.initialize(KEY_SIZE)
                keyPair = keyPairGenerator.generateKeyPair()
            } catch (e: Exception) {
                throw IllegalStateException(e)
            }
            return keyPair
        }
    }
}