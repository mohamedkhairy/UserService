package com.example.userservice.presentation.details

import Route
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.userservice.presentation.component.imagePainter
import com.example.userservice.presentation.details.DataKey.USER_ID_KEY
import com.example.userservice.presentation.sevice.MyServiceViewModel

@Composable
fun UserDetailsScreen(
    navController: NavController,
    serviceViewModel: MyServiceViewModel,
) {

    val userId by remember {
        mutableStateOf(navController.previousBackStackEntry?.savedStateHandle?.get<String>(USER_ID_KEY)!!)
    }

    val userInfoViewModel: UserInfoViewModel = hiltViewModel()
    val usersDetails = userInfoViewModel.userInfoState.value
    val loading = userInfoViewModel.loading.value



    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.LightGray)


    ) {


        LaunchedEffect(key1 = Unit){
            userInfoViewModel.getUserInfoById(userId)
        }


        if (loading)
            CircularProgressIndicator(  modifier = Modifier
                .background(Color.LightGray)
                .requiredWidth(30.dp).requiredHeight(30.dp))


        usersDetails?.let {

            Image(
                painter = imagePainter(imageUrl = it.avatar),
                contentDescription = "user image",
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(150.dp)
                    .padding(8.dp)
            )


            Text(
                text = "${it.firstName} ${it.lastName}",
                color = Color.DarkGray,
                style = MaterialTheme.typography.h5,
                fontSize = 18.sp
            )

            Spacer(modifier = Modifier.padding(5.dp))

            Text(
                text = it.email,
                color = Color.DarkGray,
                fontSize = 16.sp,
                style = MaterialTheme.typography.h5,
                maxLines = 2
            )

            Spacer(modifier = Modifier.padding(5.dp))

            TimerText(serviceViewModel = serviceViewModel) {
                serviceViewModel.resetTimer()
                navController.popBackStack(Route.HOME.name , false)
            }
        }

    }

}


@Composable
fun TimerText(serviceViewModel: MyServiceViewModel , closeAndBack:() -> Unit){

    var isServiceStarted by remember { mutableStateOf(false) }
    val timer = serviceViewModel.progressTime.value


    if (!isServiceStarted){
        isServiceStarted = true
        serviceViewModel.getProgress()
    }

    if (timer == 5) { closeAndBack() }

    Text(
        text = "time: $timer",
        color = Color.DarkGray
    )

}

object DataKey{
    const val USER_ID_KEY = "USER_ID"
}