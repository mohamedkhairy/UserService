package com.example.userservice.data.source.mapper

import com.example.userservice.data.source.remote.response.UserInfoData
import com.example.userservice.data.utils.ModelMapper
import com.example.userservice.domain.entity.UserInfo


class UserInfoDomainMapper : ModelMapper<UserInfoData, UserInfo> {

    override fun mappingModel(model: UserInfoData): UserInfo {
        return UserInfo(
            model.avatar,
            model.email,
            model.firstName,
            model.id,
            model.lastName
        )

    }


    fun toDomainModel(initial: UserInfoData?): UserInfo? {
        return initial?.let { mappingModel(it) }
    }


}

