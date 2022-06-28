package com.example.userservice.data.source.remote.service.allUsers

import com.example.userservice.data.source.remote.response.AllUsersResponse


interface AllUsersService {

    suspend fun callAllUsers(): AllUsersResponse


}
