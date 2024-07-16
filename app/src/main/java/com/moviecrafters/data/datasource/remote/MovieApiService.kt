package com.moviecrafters.data.datasource.remote

import com.moviecrafters.data.datasource.remote.dto.BaseMovieResponse
import com.moviecrafters.data.datasource.remote.dto.PopularPeopleResponse
import com.moviecrafters.data.util.ApiConstants
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.QueryMap

interface MovieApiService {
    @GET("trending/movie/{dayOrWeek}")
    suspend fun getTrendingMovies(
        @Path("dayOrWeek") dayOrWeek: String,
        @QueryMap options: Map<String, String>
    ): BaseMovieResponse


    @GET("movie/{eventType}")
    suspend fun getMoviesType(
        @Path("eventType") eventType: String,
        @QueryMap options: Map<String, String>
    ): BaseMovieResponse

    @GET("tv/{tvSeriesType}")
    suspend fun fetchTvSeries(
        @Path("tvSeriesType") tvSeriesType: String,
        @QueryMap options: Map<String, String>
    ): BaseMovieResponse

    //https://api.themoviedb.org/3/person/popular?language=en-US&page=1
    @GET("person/{popular}")
    suspend fun fetchPopularPeoples(
        @Path("popular") popular: String,
        @QueryMap options: Map<String, String>
    ): PopularPeopleResponse
}


//https://api.themoviedb.org/3/movie/upcoming?language=en-US&page=1