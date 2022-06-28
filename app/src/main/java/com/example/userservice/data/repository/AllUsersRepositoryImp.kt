package com.example.userservice.data.repository

import com.example.userservice.data.source.local.dao.UserDAO
import com.example.userservice.data.source.mapper.AllUsersDomainMapper
import com.example.userservice.data.source.mapper.UserTableMapper
import com.example.userservice.data.source.remote.service.allUsers.AllUsersService
import com.example.userservice.data.utils.networkBoundResource
import com.example.userservice.domain.core.Resource
import com.example.userservice.domain.entity.User
import com.example.userservice.domain.repository.AllUsersRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject


class AllUsersRepositoryImp @Inject constructor(
    private val clientService: AllUsersService,
    private val userDAO: UserDAO,
    private val domainMapper: AllUsersDomainMapper,
    private val daoMapper: UserTableMapper

) : AllUsersRepository {

    override suspend fun getAllUsers(): Flow<Resource<MutableList<User>?>> =
        networkBoundResource(
            query = {
                 flow {
                    val result = userDAO.getAllUsers()
                     val mappedData = domainMapper.toDomainList(result)
                      emit(mappedData)
                 }.flowOn(Dispatchers.IO)
            },
            fetchFromApi = {
                val response = clientService.callAllUsers()
                daoMapper.toEntityList(response.userData)
            },
            saveFetchResult = { data ->
                data?.map {
                    userDAO.insertUser(it)
                }
                domainMapper.toDomainList(data?.toMutableList())
            }
        ).onStart {
            emit(Resource.Loading(null))
        }
            .flowOn(Dispatchers.IO)

}