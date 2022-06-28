package com.example.userservice.domain.repository

import com.example.userservice.domain.core.Resource
import com.example.userservice.domain.entity.UserInfo
import kotlinx.coroutines.flow.Flow

interface UserInfoRepository {

    suspend fun getUserInfo(userId: String): Flow<Resource<UserInfo>>

}