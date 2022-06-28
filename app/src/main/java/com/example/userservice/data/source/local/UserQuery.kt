package com.example.userservice.data.source.local

object UserQuery {

    const val TABLE_NAME = "all_users"
    private const val COMMA_SEP = ", "

    private const val COLUMN_ID_TYPE = "INTEGER PRIMARY KEY"
     const val COLUMN_ID = "id"

    private const val COLUMN_AVATAR_TYPE = "TEXT"
    const val COLUMN_AVATAR = "avatar"

    private const val COLUMN_EMAIL_TYPE = "TEXT"
     const val COLUMN_EMAIL = "email"

    private const val COLUMN_FIRST_NAME_TYPE = "TEXT"
     const val COLUMN_FIRST_NAME = "firstName"

    private const val COLUMN_LAST_NAME_TYPE = "TEXT"
     const val COLUMN_LAST_NAME = "lastName"


    const val SQL_CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " (" +
            COLUMN_ID + " " + COLUMN_ID_TYPE + COMMA_SEP +
            COLUMN_AVATAR + " " + COLUMN_AVATAR_TYPE + COMMA_SEP +
            COLUMN_EMAIL + " " + COLUMN_EMAIL_TYPE + COMMA_SEP +
            COLUMN_FIRST_NAME + " " + COLUMN_FIRST_NAME_TYPE + COMMA_SEP +
            COLUMN_LAST_NAME + " " + COLUMN_LAST_NAME_TYPE + ")"


    const val SQL_DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME



}