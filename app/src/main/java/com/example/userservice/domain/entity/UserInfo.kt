package com.example.userservice.domain.entity

import kotlinx.serialization.SerialName

data class UserInfo(
    val avatar: String,
    val email: String,
    val firstName: String,
    val id: Int,
    val lastName: String
)