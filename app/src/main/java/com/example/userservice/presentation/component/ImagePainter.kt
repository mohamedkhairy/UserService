package com.example.userservice.presentation.component

import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.example.userservice.R


@Composable
fun imagePainter(imageUrl: String): AsyncImagePainter {


    val painter = rememberAsyncImagePainter(
        model = ImageRequest.Builder(LocalContext.current)
            .error(R.drawable.placeholder)
            .data(imageUrl)
            .size(300)
            .crossfade(true)
            .crossfade(200)
            .build()
)

    if (painter.state is AsyncImagePainter.State.Loading) {
        CircularProgressIndicator()
    }


    return painter
}