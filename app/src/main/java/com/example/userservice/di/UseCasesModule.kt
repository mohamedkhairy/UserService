package com.example.userservice.di

import com.example.userservice.domain.repository.AllUsersRepository
import com.example.userservice.domain.repository.UserInfoRepository
import com.example.userservice.domain.useCases.AllUsersUseCase
import com.example.userservice.domain.useCases.UserInfoUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object UseCasesModule {



        @ViewModelScoped
        @Provides
        fun provideArticlesUseCase(allUsersRepository : AllUsersRepository) : AllUsersUseCase {
            return AllUsersUseCase(allUsersRepository)
        }

    @ViewModelScoped
    @Provides
    fun provideUserInfoUseCase(userInfoRepository : UserInfoRepository) : UserInfoUseCase {
        return UserInfoUseCase(userInfoRepository)
    }


}