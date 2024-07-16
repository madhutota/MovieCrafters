package com.moviecrafters.data.datasource.remote.dto


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BaseMovieResponse(
    @SerialName("dates")
    val dates: Dates? = null,
    @SerialName("page")
    val page: Int?=null,
    @SerialName("results")
    val results: List<TrendingDto> = emptyList(),
    @SerialName("total_pages")
    val totalPages: Int?=null,
    @SerialName("total_results")
    val totalResults: Int?=null
)