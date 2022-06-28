package com.example.userservice.data.source.remote.service.userInfo

import com.example.userservice.data.source.remote.response.UserInfoResponse

interface UserInfoService {

    suspend fun callUserInformation(userId: String): UserInfoResponse


}
