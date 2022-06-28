package com.example.userservice.presentation

import NavigationSystem
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.example.userservice.presentation.component.MainAppBar
import com.example.userservice.presentation.sevice.MyService
import com.example.userservice.presentation.sevice.MyServiceViewModel
import com.example.userservice.presentation.ui.theme.UserServiceTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val serviceViewModel: MyServiceViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UserServiceTheme {

                val context = LocalContext.current
                val scaffoldState = rememberScaffoldState()
                Scaffold(
                    scaffoldState = scaffoldState,
                    topBar = {
                        MainAppBar()
                    }

                ) {

                    NavigationSystem(serviceViewModel)

                }
            }

        }


    }


    override fun onResume() {
        super.onResume()
        startService()
    }


    override fun onStop() {
        super.onStop()
        unbindService(serviceViewModel.serviceConnection)
    }

    private fun startService() {
        val serviceIntent = Intent(this, MyService::class.java)
        startService(serviceIntent)
        bindService()
    }

    private fun bindService() {
        val serviceBindIntent = Intent(this, MyService::class.java)
        bindService(serviceBindIntent, serviceViewModel.serviceConnection, Context.BIND_AUTO_CREATE)
    }

}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    UserServiceTheme {
        Greeting("Android")
    }
}