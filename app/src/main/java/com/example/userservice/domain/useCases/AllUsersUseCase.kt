package com.example.userservice.domain.useCases


import kotlinx.coroutines.flow.Flow
import com.example.userservice.domain.core.Resource
import com.example.userservice.domain.entity.User
import com.example.userservice.domain.repository.AllUsersRepository
import javax.inject.Inject

class AllUsersUseCase @Inject constructor(
    private val allUsersRepository: AllUsersRepository
) {

    suspend fun invoke(): Flow<Resource<MutableList<User>?>> =
        allUsersRepository.getAllUsers()


}
