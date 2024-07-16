package com.moviecrafters.domain.model

import androidx.compose.runtime.Immutable

@Immutable
data class People(
    val adult: Boolean? = null,
    val gender: Int? = null,
    val id: Int? = null,
    val knownFor: List<KnownFor> = emptyList(),
    val knownForDepartment: String? = null,
    val name: String? = null,
    val originalName: String? = null,
    val popularity: Double? = null,
    val profilePath: String? = null
)