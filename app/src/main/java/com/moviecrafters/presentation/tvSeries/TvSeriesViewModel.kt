package com.moviecrafters.presentation.tvSeries

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.PagingState
import androidx.paging.cachedIn
import com.moviecrafters.domain.model.Trending
import com.moviecrafters.domain.repository.MovieRepository
import com.moviecrafters.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TvSeriesViewModel @Inject constructor(var repository: MovieRepository) : BaseViewModel() {

    private var _tvSeriesList = MutableStateFlow<PagingData<Trending>>(PagingData.empty())
    var tvSeriesList = _tvSeriesList.asStateFlow()


    override fun onTabSelected(tabQuery: String) {
        getTvSeriesList(tabQuery)
    }

    private fun getTvSeriesList(query: String) {
        viewModelScope.launch {
            repository.getTvSeriesData(query).cachedIn(viewModelScope)
                .collectLatest { pagingData ->
                    _tvSeriesList.value = pagingData
                }
        }
    }
}