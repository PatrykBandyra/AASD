package com.example.api.config

import com.nimbusds.jose.jwk.RSAKey
import java.security.KeyPair
import java.security.interfaces.RSAPrivateKey
import java.security.interfaces.RSAPublicKey
import java.util.*

internal class Jwks {
    internal companion object {
        internal fun generateRsa(): RSAKey {
            val keyPair: KeyPair? = KeyGeneratorUtils.generateRsaKey()
            val publicKey: RSAPublicKey = keyPair?.public as RSAPublicKey
            val privateKey: RSAPrivateKey = keyPair.private as RSAPrivateKey
            return RSAKey.Builder(publicKey)
                    .privateKey(privateKey)
                    .keyID(UUID.randomUUID().toString())
                    .build()
        }
    }
}