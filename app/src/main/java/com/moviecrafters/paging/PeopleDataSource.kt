package com.moviecrafters.paging

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.moviecrafters.data.datasource.remote.MovieApiService
import com.moviecrafters.data.mapper.toDomain
import com.moviecrafters.data.mapper.toPeopleDomain
import com.moviecrafters.data.util.getCommonParameters
import com.moviecrafters.domain.model.People
import com.moviecrafters.domain.model.Trending

class PeopleDataSource(var query: String, var apiService: MovieApiService) :
    PagingSource<Int, People>() {

    var TAG = "PeopleDataSource"

    companion object {
        private const val STARTING_PAGE_INDEX = 1
    }


    override fun getRefreshKey(state: PagingState<Int, People>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, People> {

        val currentPage = params.key ?: STARTING_PAGE_INDEX
        val commonParams = getCommonParameters(currentPage)
        return try {
            val result = apiService.fetchPopularPeoples(query, commonParams)
            val endOfPaginationReached = result.results.isEmpty()

            LoadResult.Page(
                data = result.results.toPeopleDomain(),
                prevKey = if (currentPage == STARTING_PAGE_INDEX) null else currentPage - 1,
                nextKey = if (endOfPaginationReached) null else currentPage + 1
            )
        } catch (e: Exception) {
            Log.e(TAG, e.toString())
            LoadResult.Error(e)
        }


    }

}