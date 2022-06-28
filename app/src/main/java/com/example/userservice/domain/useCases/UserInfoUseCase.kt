package com.example.userservice.domain.useCases

import com.example.userservice.domain.core.Resource
import com.example.userservice.domain.entity.User
import com.example.userservice.domain.entity.UserInfo
import com.example.userservice.domain.repository.AllUsersRepository
import com.example.userservice.domain.repository.UserInfoRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserInfoUseCase @Inject constructor(
    private val userInfoRepository: UserInfoRepository
) {

    suspend fun invoke(userId: String): Flow<Resource<UserInfo>> =
        userInfoRepository.getUserInfo(userId)


}
