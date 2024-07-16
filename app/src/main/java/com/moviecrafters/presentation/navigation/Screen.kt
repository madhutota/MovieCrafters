package com.moviecrafters.presentation.navigation

import com.moviecrafters.domain.model.Trending
import kotlinx.serialization.Serializable

@Serializable
sealed class Screen {
    @Serializable
    data class MovieDetail(var imageUrl:String) : Screen()
}