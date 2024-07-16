package com.moviecrafters.presentation.tvSeries

import android.util.Log
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.paging.compose.collectAsLazyPagingItems
import com.moviecrafters.domain.model.Trending
import com.moviecrafters.presentation.components.CustomTab
import com.moviecrafters.presentation.components.MovieListCard
import com.moviecrafters.presentation.components.tvSeriesList
import com.moviecrafters.presentation.home.HomeTabActions
import com.moviecrafters.presentation.home.MovieBottomBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TVSeries(
    tvSeriesViewModel: TvSeriesViewModel,
    onNavigateToRoute: (String) -> Unit,
    movieItemClick: (Trending) -> Unit
    ) {

    val snackCollections = remember { null }
    val filters = remember { null }
    Scaffold(
        bottomBar = {
            MovieBottomBar(
                tabs = HomeTabActions.entries.toTypedArray(),
                currentRoute = HomeTabActions.SERIES.route,
                navigateToRoute = onNavigateToRoute
            )
        },
        modifier = Modifier.fillMaxSize()
    ) { paddingValues ->
        Surface(modifier = Modifier.padding(paddingValues)) {
            var selectedTabIndex by remember {
                mutableIntStateOf(0)
            }
            val movieCategories = listOf("airing_today", "on_the_air", "popular", "top_rated")
            LaunchedEffect(selectedTabIndex) {
                // Fetch initial data when Composable is first loaded
                val initialQuery = movieCategories.getOrElse(selectedTabIndex) { "airing_today" }
                tvSeriesViewModel.onTabSelected(initialQuery)
            }


            CustomTab(selectedTabIndex = selectedTabIndex, onTabClick = {
                selectedTabIndex = it

            }, tabItems = tvSeriesList) {
                val trendingMovies = tvSeriesViewModel.tvSeriesList.collectAsLazyPagingItems()
                MovieListCard(trendingMovies, movieItemClick = movieItemClick)
            }
        }

    }

}