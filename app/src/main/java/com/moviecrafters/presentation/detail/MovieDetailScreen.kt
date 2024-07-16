package com.moviecrafters.presentation.detail

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.moviecrafters.data.util.ApiConstants
import com.moviecrafters.domain.model.Trending
import com.moviecrafters.presentation.components.CustomLoader
import com.moviecrafters.presentation.util.NetworkImageCustom


@RequiresApi(Build.VERSION_CODES.S)
@Composable
fun MovieDetailScreen(imageUrl: String) {

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CustomLoader()
        /*NetworkImageCustom(
            modifier = Modifier.size(200.dp),
            url = ApiConstants.IMAGE_URL.plus(imageUrl)
        )*/
    }


}