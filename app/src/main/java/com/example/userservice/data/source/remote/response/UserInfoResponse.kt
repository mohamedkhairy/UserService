package com.example.userservice.data.source.remote.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class UserInfoResponse(
    @SerialName("data")
    val userInfoData: UserInfoData,
    @SerialName("support")
    val support: UserSupport
)


@Serializable
data class UserInfoData(
    @SerialName("avatar")
    val avatar: String,
    @SerialName("email")
    val email: String,
    @SerialName("first_name")
    val firstName: String,
    @SerialName("id")
    val id: Int,
    @SerialName("last_name")
    val lastName: String
)


@Serializable
data class UserSupport(
    @SerialName("text")
    val text: String,
    @SerialName("url")
    val url: String
)