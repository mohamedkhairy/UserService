package com.example.userservice.di

import com.example.userservice.data.repository.UserInfoRepositoryImp
import com.example.userservice.data.source.mapper.UserInfoDomainMapper
import com.example.userservice.data.source.remote.service.userInfo.UserInfoService
import com.example.userservice.domain.repository.UserInfoRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object UserInfoModule {
    @ViewModelScoped
    @Provides
    fun provideUserInfoRepository(userInfoService: UserInfoService,
                                 infoDomainMapper: UserInfoDomainMapper
    ) : UserInfoRepository {
        return UserInfoRepositoryImp(userInfoService, infoDomainMapper)
    }
}
