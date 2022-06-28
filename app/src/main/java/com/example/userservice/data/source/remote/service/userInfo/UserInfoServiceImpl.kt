package com.example.userservice.data.source.remote.service.userInfo

import com.example.userservice.data.source.remote.EndPoints
import com.example.userservice.data.source.remote.response.UserInfoResponse
import io.ktor.client.*
import io.ktor.client.request.*
import javax.inject.Inject

class UserInfoServiceImpl @Inject constructor(val httpClient: HttpClient):
    UserInfoService {

    override suspend fun callUserInformation(userId: String): UserInfoResponse {
        return httpClient.get<UserInfoResponse> {
            url(EndPoints.USER_INFO + userId)
        }
    }


}