package com.example.userservice.presentation.sevice

import android.app.Service
import android.os.IBinder
import com.example.userservice.presentation.sevice.MyService.MyBinder
import android.content.Intent
import android.os.Binder
import android.os.Handler
import android.util.Log
import com.example.userservice.presentation.sevice.MyService
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MyService : Service() {
    private val mBinder: IBinder = MyBinder()

    override fun onCreate() {
        super.onCreate()
    }

    override fun onBind(intent: Intent): IBinder? {
        return mBinder
    }

    inner class MyBinder : Binder() {
        val service: MyService
            get() = this@MyService
    }


    fun getProgress(): Flow<Int>{
        var progress = 0
        return flow {
            while (progress < 5) {
                progress++
                delay(1000)
                emit(progress)
            }
        }
    }
    override fun onTaskRemoved(rootIntent: Intent) {
        super.onTaskRemoved(rootIntent)
        Log.d(TAG, "onTaskRemoved: called.")
        stopSelf()
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy: called.")
    }

    companion object {
        private const val TAG = "MyService"
    }
}