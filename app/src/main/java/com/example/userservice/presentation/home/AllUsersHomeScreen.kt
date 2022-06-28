package com.example.userservice.presentation.home

import Route
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.userservice.presentation.component.UserCardItem
import com.example.userservice.presentation.details.DataKey.USER_ID_KEY

@Composable
fun AllUsersHomeScreen(
    navController: NavController) {
    val allUsersViewModel: AllUsersViewModel = hiltViewModel()
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.LightGray
    ) {
        val usersList = allUsersViewModel.usersList.value
        val loading = allUsersViewModel.loading.value

        if (loading)
                CircularProgressIndicator(  modifier = Modifier
                    .background(Color.LightGray)
                    .requiredWidth(30.dp).requiredHeight(30.dp))



        LazyColumn {
            itemsIndexed(items = usersList) { index, item ->
                UserCardItem(user = item){

                    navController.currentBackStackEntry?.savedStateHandle?.apply {
                        set(USER_ID_KEY, it)
                    }


                    navController.navigate(Route.DETAILS.name)
                }
            }
        }
    }
}
