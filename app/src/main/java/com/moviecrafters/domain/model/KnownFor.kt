package com.moviecrafters.domain.model

import kotlinx.serialization.SerialName

data class KnownFor(
    val adult: Boolean?=null,
    val backdropPath: String?=null,
    val firstAirDate: String?=null,
    val genreIds: List<Int> = emptyList(),
    val id: Int?=null,
    val mediaType: String?=null,
    val name: String?=null,
    val originCountry: List<String> = emptyList(),
    val originalLanguage: String?=null,
    val originalName: String?=null,
    val originalTitle: String?=null,
    val overview: String?=null,
    val popularity: Double?=null,
    val posterPath: String?=null,
    val releaseDate: String?=null,
    val title: String?=null,
    val video: Boolean?=null,
    val voteAverage: Double?=null,
    val voteCount: Int?=null


)