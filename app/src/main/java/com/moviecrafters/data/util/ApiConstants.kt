package com.moviecrafters.data.util

import com.moviecrafters.BuildConfig

object ApiConstants {
    const val API_KEY = BuildConfig.MOVIE_DB_API_KEY
    const val BASE_URL = "https://api.themoviedb.org/3/"
    const val ITEMS_PER_PAGE = 10
    const val IMAGE_URL = "https://image.tmdb.org/t/p/w342"
    const val DUMMY = "https://image.tmdb.org/t/p/w342/zszRKfzjM5jltiq8rk6rasKVpUv.jpg"

    const val DEFAULT_LANGUAGE = "en-US"
}


fun getCommonParameters(page: Int, language: String = ApiConstants.DEFAULT_LANGUAGE, apiKey: String = ApiConstants.API_KEY): Map<String, String> {
    return mapOf(
        "page" to page.toString(),
        "language" to language,
        "api_key" to apiKey
    )
}