package com.moviecrafters.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.moviecrafters.data.datasource.remote.MovieApiService
import com.moviecrafters.data.util.ApiConstants.ITEMS_PER_PAGE
import com.moviecrafters.domain.model.People
import com.moviecrafters.domain.model.Trending
import com.moviecrafters.domain.repository.MovieRepository
import com.moviecrafters.paging.PeopleDataSource
import com.moviecrafters.paging.TrendingDataSource
import com.moviecrafters.paging.TvSeriesDataSource
import com.moviecrafters.paging.UpcomingDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(private var apiService: MovieApiService) :
    MovieRepository {
    /*override suspend fun getTrendingMovies(): Flow<Result<TrendingResponseDTO, RootError>>

        flow {
        emit(Result.Loading)
        try {
            val result = apiService.getTrendingMovies("day", 1)
            emit(Result.Success(result))
        } catch (e: HttpException) {
            val errorMessage = when (e.code()) {
                408 -> "Request Timeout"
                413 -> "Payload Too Large"
                else -> "HTTP ${e.code()}: ${e.message()}"
            }
            emit(Result.Error(RootError.Api(e.code(), errorMessage)))
        } catch (e: IOException) {
            emit(Result.Error(RootError.Network("Network error: ${e.message}")))
        } catch (e: Exception) {
            emit(Result.Error(RootError.Unknown("Unknown error: ${e.message}")))
        }
    }.flowOn(Dispatchers.IO)

        {


    return Pager(
    config = PagingConfig(pageSize = ITEMS_PER_PAGE),
    pagingSourceFactory = { TrendingDataSource("day",apiService) }
    ).flow
        }*/

    override suspend fun getTrendingMovies(query: String): Flow<PagingData<Trending>> {
        Result
        return Pager(
            config = PagingConfig(pageSize = 20),
            pagingSourceFactory = { TrendingDataSource(query, apiService) }
        ).flow
    }

    override suspend fun getMovies(moviesType: String): Flow<PagingData<Trending>> {
        return Pager(
            config = PagingConfig(pageSize = ITEMS_PER_PAGE),
            pagingSourceFactory = { UpcomingDataSource(moviesType, apiService) }
        ).flow
    }

    override suspend fun getTvSeriesData(query: String): Flow<PagingData<Trending>> {
        return Pager(
            config = PagingConfig(pageSize = ITEMS_PER_PAGE),
            pagingSourceFactory = { TvSeriesDataSource(query, apiService) }
        ).flow
    }

    override suspend fun getPopularPeopleData(query: String): Flow<PagingData<People>> {
        return Pager(
            config = PagingConfig(pageSize = ITEMS_PER_PAGE),
            pagingSourceFactory = { PeopleDataSource(query, apiService) }
        ).flow
    }


}

