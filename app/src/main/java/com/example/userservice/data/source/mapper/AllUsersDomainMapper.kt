package com.example.userservice.data.source.mapper

import com.example.userservice.data.source.local.UserTable
import com.example.userservice.data.utils.ModelMapper
import com.example.userservice.data.source.remote.response.UserData
import com.example.userservice.domain.entity.User


class AllUsersDomainMapper : ModelMapper<UserTable, User> {

    override fun mappingModel(model: UserTable): User {
        return User(
            model.avatar,
            model.email,
            model.firstName,
            model.id,
            model.lastName
        )

    }


    fun toDomainList(initial: MutableList<UserTable>?): MutableList<User>? {
        return initial?.map { mappingModel(it) }?.toMutableList()
    }


}

