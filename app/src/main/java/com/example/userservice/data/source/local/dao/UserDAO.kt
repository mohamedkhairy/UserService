package com.example.userservice.data.source.local.dao

import com.example.userservice.data.source.local.UserTable

interface UserDAO {

    fun getAllUsers(): ArrayList<UserTable>?
    fun insertUser(userTable: UserTable): Boolean
    fun findUserById(userId: Int): UserTable?
    fun updateUser(userTable: UserTable): Boolean


}