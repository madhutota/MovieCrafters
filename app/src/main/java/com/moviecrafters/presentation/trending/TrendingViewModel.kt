package com.moviecrafters.presentation.trending

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.moviecrafters.domain.model.Trending
import com.moviecrafters.domain.repository.MovieRepository
import com.moviecrafters.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TrendingViewModel @Inject constructor(private val repo: MovieRepository) : BaseViewModel() {
    private val _trendingFlow = MutableStateFlow<PagingData<Trending>>(PagingData.empty())
    val trendingFlow: StateFlow<PagingData<Trending>> = _trendingFlow
    private fun getTrendingApi(query: String) {
        viewModelScope.launch {
            Log.e("Api Query ", query)
            repo.getTrendingMovies(query).cachedIn(viewModelScope)
                .collectLatest { pagingData ->
                    _trendingFlow.value = pagingData
                }
        }
    }
    override fun onTabSelected(tabQuery: String) {
        getTrendingApi(tabQuery)
    }

}