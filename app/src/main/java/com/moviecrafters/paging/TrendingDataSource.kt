package com.moviecrafters.paging

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.moviecrafters.data.datasource.remote.MovieApiService
import com.moviecrafters.data.mapper.toDomain
import com.moviecrafters.data.util.getCommonParameters
import com.moviecrafters.domain.model.Trending

class TrendingDataSource(var query: String, private var apiService: MovieApiService) :
    PagingSource<Int, Trending>() {

    companion object {
        private const val STARTING_PAGE_INDEX = 1
    }


    override fun getRefreshKey(state: PagingState<Int, Trending>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Trending> {

        val currentPage = params.key ?: STARTING_PAGE_INDEX
        val commonParams = getCommonParameters(currentPage)
        return try {
            val result = apiService.getTrendingMovies(query, commonParams)
            Log.d("Api Data", "----- ${result.results}")
            Log.d("Api Data2", "----- ${result.results.toDomain()}")
            val endOfPaginationReached = result.results.isEmpty()

            LoadResult.Page(
                data = result.results.toDomain(),
                prevKey = if (currentPage == STARTING_PAGE_INDEX) null else currentPage - 1,
                nextKey = if (endOfPaginationReached) null else currentPage + 1
            )
        } catch (e: Exception) {
            Log.d("Api Data Error", "----- $e")
            LoadResult.Error(e)
        }


    }

}