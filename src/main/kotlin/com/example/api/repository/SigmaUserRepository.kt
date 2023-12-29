package com.example.api.repository

import com.example.api.model.SigmaUser
import org.springframework.data.jpa.repository.JpaRepository

interface SigmaUserRepository : JpaRepository<SigmaUser, Long> {

    fun findSigmaUserByUsername(username: String): SigmaUser?
}