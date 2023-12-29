package com.example.api.model

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.FetchType
import jakarta.persistence.Id
import jakarta.persistence.ManyToMany
import jakarta.persistence.Table

@Entity
@Table(name = "SIGMA_AUTHORITIES")
class SigmaAuthority(

        @Id
        @Enumerated(EnumType.STRING)
        @Column(unique = true, name = "name")
        val name: SigmaAuthorityValue,

        @ManyToMany(mappedBy = "authorities", fetch = FetchType.LAZY)
        val users: MutableSet<SigmaUser> = mutableSetOf(),
) {
    enum class SigmaAuthorityValue(val value: String) {
        USER_READ("USER_READ"),
        USER_WRITE("USER_WRITE"),
        USER_DELETE("USER_DELETE"),

        OWNER_READ("OWNER_READ"),
        OWNER_WRITE("OWNER_WRITE"),
        OWNER_DELETE("OWNER_DELETE"),

        ADMIN_READ("ADMIN_READ"),
        ADMIN_WRITE("ADMIN_WRITE"),
        ADMIN_DELETE("ADMIN_DELETE")
    }
}