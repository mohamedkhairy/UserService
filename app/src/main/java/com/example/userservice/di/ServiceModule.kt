package com.example.userservice.di


import com.example.userservice.data.source.remote.service.allUsers.AllUsersService
import com.example.userservice.data.source.remote.service.allUsers.AllUsersServiceImpl
import com.example.userservice.data.source.remote.service.userInfo.UserInfoService
import com.example.userservice.data.source.remote.service.userInfo.UserInfoServiceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import io.ktor.client.*

@Module
@InstallIn(ViewModelComponent::class)
object ServiceModule {

    @ViewModelScoped
    @Provides
    fun provideAllUsersService(httpClient: HttpClient): AllUsersService {
        return AllUsersServiceImpl(httpClient)
    }

    @ViewModelScoped
    @Provides
    fun provideUserInfoService(httpClient: HttpClient): UserInfoService {
        return UserInfoServiceImpl(httpClient)
    }

}