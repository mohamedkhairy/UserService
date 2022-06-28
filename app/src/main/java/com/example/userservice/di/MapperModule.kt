package com.example.userservice.di

import com.example.userservice.data.source.mapper.AllUsersDomainMapper
import com.example.userservice.data.source.mapper.UserInfoDomainMapper
import com.example.userservice.data.source.mapper.UserTableMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(ViewModelComponent::class)
object MapperModule {

    @ViewModelScoped
    @Provides
    fun provideAllUsersDomainMapper (): AllUsersDomainMapper {
        return AllUsersDomainMapper()
    }

    @ViewModelScoped
    @Provides
    fun provideUserInfoDomainMapper (): UserInfoDomainMapper {
        return UserInfoDomainMapper()
    }

    @ViewModelScoped
    @Provides
    fun provideUserTableMapper (): UserTableMapper {
        return UserTableMapper()
    }
}