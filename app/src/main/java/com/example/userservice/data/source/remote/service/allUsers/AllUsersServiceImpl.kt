package com.example.userservice.data.source.remote.service.allUsers


import com.example.userservice.data.source.remote.EndPoints
import com.example.userservice.data.source.remote.response.AllUsersResponse
import io.ktor.client.*
import io.ktor.client.request.*
import javax.inject.Inject

class AllUsersServiceImpl @Inject constructor(val httpClient: HttpClient):
    AllUsersService {



    override suspend fun callAllUsers(): AllUsersResponse {
        return httpClient.get<AllUsersResponse> {
            url(EndPoints.ALL_USERS)
        }
    }


}