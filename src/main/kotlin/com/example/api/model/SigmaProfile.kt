package com.example.api.model

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.OneToOne
import jakarta.persistence.Table

@Entity
@Table(name = "SIGMA_PROFILE")
class SigmaProfile(

        @Column(name = "firstName")
        val firstName: String,

        @Column(name = "lastName")
        val lastName: String,

        @OneToOne(mappedBy = "profile", fetch = FetchType.EAGER)
        @JoinColumn(name = "SIGMA_USERS_id", referencedColumnName = "id")
        val user: SigmaUser? = null,

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id")
        val id: Long? = null
)