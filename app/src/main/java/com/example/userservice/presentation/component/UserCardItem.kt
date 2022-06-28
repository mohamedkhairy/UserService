package com.example.userservice.presentation.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.userservice.domain.entity.User

@Composable
fun UserCardItem(
    user: User,
    goToUserDetails: (String) -> Unit
) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable {goToUserDetails("${user.id}")}
    ) {



            Row(
                verticalAlignment = Alignment.CenterVertically
            )
            {

                Image(
                    painter = imagePainter(imageUrl = user.avatar),
                    contentDescription = "user image",
                    modifier = Modifier
                        .size(80.dp, 80.dp)
                        .padding(8.dp)
                )

                Column(
                    Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.Center
                ) {

                    Text(
                        text = "${user.firstName} ${user.lastName}",
                        color = Color.DarkGray,
                        fontSize = 18.sp
                    )

                    Spacer(modifier = Modifier.padding(5.dp))

                    Text(
                        text = user.email,
                        color = Color.DarkGray,
                        fontSize = 14.sp,
                        maxLines = 2
                    )

                }



        }
    }
}