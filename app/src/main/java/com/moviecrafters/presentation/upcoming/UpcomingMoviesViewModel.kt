package com.moviecrafters.presentation.upcoming

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.moviecrafters.domain.model.Trending
import com.moviecrafters.domain.repository.MovieRepository
import com.moviecrafters.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UpcomingMoviesViewModel @Inject constructor(private var repo: MovieRepository) :
    BaseViewModel() {
    private val _upcomingFlow = MutableStateFlow<PagingData<Trending>>(PagingData.empty())
    val upcomingFlow: StateFlow<PagingData<Trending>> = _upcomingFlow.asStateFlow()
    override fun onTabSelected(tabQuery: String) {
        Log.e("UpcomingMoviesViewModel",tabQuery)
        getUpcomingMovies(tabQuery)
    }

    private fun getUpcomingMovies(query: String) {
        viewModelScope.launch {
            repo.getMovies(query).cachedIn(viewModelScope).collectLatest { pagingData ->
                _upcomingFlow.value = pagingData
            }
        }
    }

}