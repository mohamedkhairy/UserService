package com.example.userservice.data.source.mapper

import com.example.userservice.data.source.local.UserTable
import com.example.userservice.data.utils.ModelMapper
import com.example.userservice.data.source.remote.response.UserData
import com.example.userservice.domain.entity.User


class UserTableMapper : ModelMapper<UserData, UserTable> {

    override fun mappingModel(model: UserData): UserTable {
        return UserTable(
            model.avatar,
            model.email,
            model.firstName,
            model.id,
            model.lastName
        )

    }


    fun toEntityList(initial: List<UserData>?): List<UserTable>? {
        return initial?.map { mappingModel(it) }
    }


}

