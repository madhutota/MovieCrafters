package com.moviecrafters.presentation.ppularPeople

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.moviecrafters.domain.model.People
import com.moviecrafters.domain.repository.MovieRepository
import com.moviecrafters.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PopularPeopleViewModel @Inject constructor(var repo: MovieRepository) : BaseViewModel() {

    var _popularPeples = MutableStateFlow<PagingData<People>>(PagingData.empty())
    var popularPeoples = _popularPeples.asStateFlow()
  override fun onTabSelected(tabQuery: String) {
        fetchPopularPeoples(tabQuery)
    }

    fun fetchPopularPeoples(query: String) {
        viewModelScope.launch {
            repo.getPopularPeopleData(query).cachedIn(viewModelScope).collectLatest { pagingData ->
                _popularPeples.value = pagingData
            }
        }
    }
}