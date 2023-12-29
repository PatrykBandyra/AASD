package com.example.api.model

import jakarta.persistence.CascadeType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.JoinTable
import jakarta.persistence.ManyToMany
import jakarta.persistence.OneToOne
import jakarta.persistence.Table

@Entity
@Table(name = "SIGMA_USERS")
class SigmaUser(

        @Column(unique = true, name = "username")
        val username: String,

        @Column(unique = true, name = "email")
        val email: String,

        @Column(name = "password")
        val password: String,

        @Enumerated(EnumType.STRING)
        @Column(name = "type")
        val type: SigmaUserType,

        @ManyToMany(cascade = [], fetch = FetchType.EAGER)
        @JoinTable(
                name = "SIGMA_USERS_SIGMA_AUTHORITIES",
                joinColumns = [JoinColumn(name = "SIGMA_USERS_id")],
                inverseJoinColumns = [JoinColumn(name = "SIGMA_AUTHORITIES_name")]
        )
        val authorities: MutableSet<SigmaAuthority> = mutableSetOf(),

        @OneToOne(cascade = [CascadeType.ALL], fetch = FetchType.EAGER)
        val profile: SigmaProfile? = null,

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id")
        val id: Long? = null
) {
    enum class SigmaUserType(val type: String) {
        USER("USER"),
        OWNER("OWNER"),
        ADMIN("ADMIN")
    }
}