package com.example.userservice.di

import android.content.Context
import com.example.userservice.data.source.local.DatabaseWrapper
import com.example.userservice.data.source.local.dao.UserDAO
import com.example.userservice.data.source.local.dao.UserDAOImp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataBaseModule {

    @Provides
    @Singleton
    fun provideDatabaseWrapper(@ApplicationContext context: Context): DatabaseWrapper{
        return DatabaseWrapper(context)
    }

    @Provides
    @Singleton
    fun provideUserDAO(databaseWrapper: DatabaseWrapper): UserDAO{
        return UserDAOImp(databaseWrapper)
    }

}