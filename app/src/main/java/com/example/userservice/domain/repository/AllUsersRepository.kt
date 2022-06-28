package com.example.userservice.domain.repository

import kotlinx.coroutines.flow.Flow
import com.example.userservice.domain.core.Resource
import com.example.userservice.domain.entity.User

interface AllUsersRepository {


    suspend fun getAllUsers(): Flow<Resource<MutableList<User>?>>


}