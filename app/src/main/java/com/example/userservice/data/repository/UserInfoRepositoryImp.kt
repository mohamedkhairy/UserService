package com.example.userservice.data.repository

import com.example.userservice.domain.core.Resource
import com.example.userservice.data.source.mapper.UserInfoDomainMapper
import com.example.userservice.data.source.remote.service.userInfo.UserInfoService
import com.example.userservice.domain.entity.UserInfo
import com.example.userservice.domain.repository.UserInfoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class UserInfoRepositoryImp @Inject constructor(
    private val userInfoService: UserInfoService,
    private val infoDomainMapper: UserInfoDomainMapper
) : UserInfoRepository {



    override suspend fun getUserInfo(userId: String): Flow<Resource<UserInfo>> =
    flow {
        try {
            val result = userInfoService.callUserInformation(userId)
            val mappedData = infoDomainMapper.toDomainModel(result.userInfoData)
            mappedData?.let {

                emit(Resource.Success(mappedData))

            } ?: emit(Resource.Error(null))


        } catch (e: Exception) {
            emit(Resource.Error(e.cause))
        }
    }

}