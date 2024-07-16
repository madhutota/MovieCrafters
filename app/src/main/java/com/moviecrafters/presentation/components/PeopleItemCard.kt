package com.moviecrafters.presentation.components

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.moviecrafters.data.util.ApiConstants
import com.moviecrafters.data.util.ApiConstants.DUMMY
import com.moviecrafters.domain.model.People
import com.moviecrafters.domain.model.Trending
import com.moviecrafters.presentation.util.NetworkImageCustom
import kotlinx.coroutines.flow.Flow

@Composable
fun PeopleItemCard(
    modifier: Modifier = Modifier, trending: People
) {
    val imageRequest =
        ImageRequest.Builder(LocalContext.current).data(trending.profilePath).crossfade(true)
            .build()
    Card(
        modifier = Modifier.padding(4.dp)
    ) {
        Box(modifier = Modifier) {

            Log.d("ImageUrl", ApiConstants.IMAGE_URL.plus(trending.profilePath))
            NetworkImageCustom(
                url = ApiConstants.IMAGE_URL.plus(trending.profilePath),
                modifier = Modifier.size(250.dp),
                contentScale = ContentScale.Crop

            )
        }


    }
}
