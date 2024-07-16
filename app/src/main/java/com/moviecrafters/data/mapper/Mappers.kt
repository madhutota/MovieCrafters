package com.moviecrafters.data.mapper

import com.moviecrafters.data.datasource.remote.dto.KnownForDto
import com.moviecrafters.data.datasource.remote.dto.PeopleDto
import com.moviecrafters.data.datasource.remote.dto.TrendingDto
import com.moviecrafters.domain.model.KnownFor
import com.moviecrafters.domain.model.People
import com.moviecrafters.domain.model.Trending

fun List<TrendingDto>.toDomain(): List<Trending> {
    return this.map { it.toDomain() }
}

fun List<PeopleDto>.toPeopleDomain(): List<People> {
    return this.map { it.toDomain() }
}


fun List<KnownForDto>.toKnownDomain(): List<KnownFor> {
    return this.map { it.toDomain() }
}


fun TrendingDto.toDomain(): Trending {
    return Trending(
        adult = this.adult,
        backdropPath = this.backdropPath,
        genreIds = this.genreIds,
        id = this.id,
        originalLanguage = this.originalLanguage,
        originalTitle = this.originalTitle,
        overview = this.overview,
        popularity = this.popularity,
        posterPath = this.posterPath,
        releaseDate = this.releaseDate,
        title = this.title,
        video = this.video,
        voteAverage = this.voteAverage,
        voteCount = this.voteCount
    )

}

fun KnownForDto.toDomain(): KnownFor {
    return KnownFor(
        adult = this.adult,
        backdropPath = this.backdropPath,
        firstAirDate = this.firstAirDate,
        genreIds = this.genreIds,
        id = this.id,
        mediaType = this.mediaType,
        name = this.name,
        originCountry = this.originCountry,
        originalLanguage = this.originalLanguage,
        originalName = this.originalName,
        originalTitle = this.originalTitle,
        overview = this.overview,
        popularity = this.popularity,
        posterPath = this.posterPath,
        releaseDate = this.releaseDate,
        title = this.title,
        video = this.video,
        voteAverage = this.voteAverage,
        voteCount = this.voteCount

    )
}

fun PeopleDto.toDomain(): People {
    return People(
        adult = this.adult,
        gender = this.gender,
        id = this.id,
        knownFor = this.knownFor.toKnownDomain(),
        knownForDepartment = this.knownForDepartment,
        name = this.name,
        originalName = this.originalName,
        popularity = this.popularity,
        profilePath = this.profilePath
    )

}