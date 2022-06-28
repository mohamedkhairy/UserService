package com.example.userservice.presentation.sevice

import android.content.ComponentName
import android.content.ServiceConnection
import android.os.IBinder
import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.userservice.presentation.sevice.MyService.MyBinder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MyServiceViewModel : ViewModel() {

    val progressTime: MutableState<Int> = mutableStateOf(0)
    private val mBinder = MutableLiveData<MyBinder?>()
    private  var myBinder: MyBinder? = null


    val serviceConnection: ServiceConnection = object : ServiceConnection {
        override fun onServiceConnected(className: ComponentName, iBinder: IBinder) {
            myBinder = iBinder as MyBinder
            mBinder.postValue(myBinder)
        }

        override fun onServiceDisconnected(arg0: ComponentName) {
            Log.d(TAG, "ServiceConnection: disconnected from service.")
            mBinder.postValue(null)
        }
    }

    fun getProgress(){
        viewModelScope.launch(Dispatchers.Main) {
            myBinder?.let {
                it.service.getProgress()
                    .collect{time ->
                    progressTime.value = time
                }
            }
        }
    }

    fun resetTimer(){
        progressTime.value = 0
    }


    companion object {
        private const val TAG = "MainActivityViewModel"
    }
}