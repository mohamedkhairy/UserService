package com.example.userservice.data.source.local.dao

import android.annotation.SuppressLint
import android.content.ContentValues
import android.database.Cursor
import com.example.userservice.data.source.local.DatabaseWrapper
import com.example.userservice.data.source.local.UserQuery.COLUMN_AVATAR
import com.example.userservice.data.source.local.UserQuery.COLUMN_EMAIL
import com.example.userservice.data.source.local.UserQuery.COLUMN_FIRST_NAME
import com.example.userservice.data.source.local.UserQuery.COLUMN_ID
import com.example.userservice.data.source.local.UserQuery.COLUMN_LAST_NAME
import com.example.userservice.data.source.local.UserQuery.TABLE_NAME
import com.example.userservice.data.source.local.UserTable
import javax.inject.Inject

class UserDAOImp @Inject constructor(
    private val databaseWrapper: DatabaseWrapper
) : UserDAO {

    override fun getAllUsers(): ArrayList<UserTable>? {
        val database = databaseWrapper.readableDatabase
        var userList: ArrayList<UserTable>? = null
        try {

            if (database != null) {
            val cursor = database.rawQuery("SELECT * FROM $TABLE_NAME", null)
            if (database.isOpen) {
                if (cursor.count > 0) {
                    userList = ArrayList<UserTable>()
                    cursor.moveToFirst()
                    while (!cursor.isAfterLast) {
                        val user: UserTable = cursorToUser(cursor)
                        userList.add(user)
                        cursor.moveToNext()
                    }
                }
                database.close()
            }
        }
        }catch (e: Exception){
            e.printStackTrace()
        }finally {
            database.close()
        }
        return userList
    }

    override fun insertUser(userTable: UserTable): Boolean {
        if (findUserById(userTable.id) != null) {
            return updateUser(userTable)
        }
        val values = userToContentValues(userTable)
        val database = databaseWrapper.writableDatabase
        var success = false
        try {
            if (database != null) {
                database.insert(TABLE_NAME, "null", values)
                success = true
            }
        } catch (ex: NullPointerException) {
            ex.printStackTrace()
        } finally {
            database?.close()
        }
        return success
    }

    override fun updateUser(userTable: UserTable): Boolean {
        val values = userToContentValues(userTable)
        val database = databaseWrapper.writableDatabase
        var success = false
        try {
            if (database != null) {
                database.update(TABLE_NAME, values, COLUMN_ID + " = " + userTable.id, null)
                success = true
            }
        } catch (ex: NullPointerException) {
            ex.printStackTrace()
        } finally {
            database?.close()
        }
        return success
    }

    override fun findUserById(userId: Int): UserTable? {
        val database = databaseWrapper.readableDatabase
        var userTable: UserTable? = null

        if (database != null) {
            val cursor =
                database.rawQuery("SELECT * FROM $TABLE_NAME WHERE $COLUMN_ID = $userId", null)
            if (cursor.count > 0) {
                cursor.moveToFirst()
                userTable = cursorToUser(cursor)
            }
            database.close()
        }
        return userTable
    }


    @SuppressLint("Range")
    private fun cursorToUser(cursor: Cursor): UserTable {
        val avatar = cursor.getString(cursor.getColumnIndex(COLUMN_AVATAR))
        val email = cursor.getString(cursor.getColumnIndex(COLUMN_EMAIL))
        val firstName = cursor.getString(cursor.getColumnIndex(COLUMN_FIRST_NAME))
        val lastName = cursor.getString(cursor.getColumnIndex(COLUMN_LAST_NAME))
        val id = cursor.getInt(cursor.getColumnIndex(COLUMN_ID))

        val userTable = UserTable(avatar, email, firstName, id, lastName)


        return userTable
    }

    private fun userToContentValues(userTable: UserTable): ContentValues {
        val values = ContentValues()
        values.put(COLUMN_ID, userTable.id)
        values.put(COLUMN_AVATAR, userTable.avatar)
        values.put(COLUMN_EMAIL, userTable.email)
        values.put(COLUMN_FIRST_NAME, userTable.firstName)
        values.put(COLUMN_LAST_NAME, userTable.lastName)
        return values
    }



}