package com.moviecrafters.domain.repository

import androidx.paging.PagingData
import com.moviecrafters.domain.model.People
import com.moviecrafters.domain.model.Trending
import kotlinx.coroutines.flow.Flow

interface MovieRepository {


    // suspend fun getTrendingMovies(): Flow<Result<TrendingResponseDTO, RootError>>

    suspend fun getTrendingMovies(query: String): Flow<PagingData<Trending>>
    suspend fun getMovies(moviesType: String):Flow<PagingData<Trending>>
    suspend fun getTvSeriesData(query:String):Flow<PagingData<Trending>>
    suspend fun getPopularPeopleData(query:String):Flow<PagingData<People>>
}