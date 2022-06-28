package com.example.userservice.data.utils

interface ModelMapper <T, DomainModel>{

    fun mappingModel(model: T): DomainModel

}