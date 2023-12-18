package com.example.LodInMicroServices.model

import jakarta.persistence.*

@Entity
@Table(name = "LoginTable")
data class LoginDTO(
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    val id:Int = -1,
    @Column(unique=true)
    val login:String = "",
    val password:String = "",
)
