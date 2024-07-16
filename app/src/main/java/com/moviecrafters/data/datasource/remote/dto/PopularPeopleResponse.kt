package com.moviecrafters.data.datasource.remote.dto


import android.provider.Contacts.People
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PopularPeopleResponse(
    @SerialName("page")
    val page: Int? = null,
    @SerialName("results")
    val results: List<PeopleDto> = emptyList(),
    @SerialName("total_pages")
    val totalPages: Int? = null,
    @SerialName("total_results")
    val totalResults: Int? = null
)