package com.example.userservice.di


import com.example.userservice.data.repository.AllUsersRepositoryImp
import com.example.userservice.data.source.local.dao.UserDAO
import com.example.userservice.data.source.mapper.AllUsersDomainMapper
import com.example.userservice.data.source.mapper.UserTableMapper
import com.example.userservice.data.source.remote.service.allUsers.AllUsersService
import com.example.userservice.domain.repository.AllUsersRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped


@Module
@InstallIn(ViewModelComponent::class)
object AllUsersModule {

    @ViewModelScoped
    @Provides
    fun provideTrendingRepository( clientService: AllUsersService,
                                   userDAO: UserDAO,
                                   domainMapper: AllUsersDomainMapper,
                                   daoMapper: UserTableMapper
    ) : AllUsersRepository {
        return AllUsersRepositoryImp(clientService, userDAO, domainMapper, daoMapper)
    }



}