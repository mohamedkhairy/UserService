package com.example.userservice.data.source.local

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject


class DatabaseWrapper @Inject constructor (@ApplicationContext private val context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    override fun onCreate(sqLiteDatabase: SQLiteDatabase) {
        Log.i(TAG, "Creating database [$DATABASE_NAME v.$DATABASE_VERSION]...")
        sqLiteDatabase.execSQL(UserQuery.SQL_CREATE_TABLE)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {

    }

    companion object {
        private const val TAG = "DatabaseWrapper"
        private const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "USERS.db"
    }
}