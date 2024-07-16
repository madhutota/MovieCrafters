package com.moviecrafters.presentation.trending

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.paging.compose.collectAsLazyPagingItems
import com.moviecrafters.domain.model.Trending
import com.moviecrafters.presentation.components.CustomTab
import com.moviecrafters.presentation.components.MovieListCard
import com.moviecrafters.presentation.components.trendingItems
import com.moviecrafters.presentation.home.HomeTabActions
import com.moviecrafters.presentation.home.MovieBottomBar


@Composable
fun Trending(
    trendingViewModel: TrendingViewModel,
    onNavigateToRoute: (String) -> Unit,
    movieItemClick: (Trending) -> Unit,
) {
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            MovieBottomBar(
                tabs = HomeTabActions.entries.toTypedArray(),
                currentRoute = HomeTabActions.TRENDING.route,
                navigateToRoute = onNavigateToRoute
            )
        },
    ) {
        Surface(modifier = Modifier.padding(it)) {
            var selectedTabIndex by remember {
                mutableIntStateOf(0)
            }
            LaunchedEffect(selectedTabIndex) {
                val query = if (selectedTabIndex == 0) "day" else "week"
                trendingViewModel.onTabSelected(query)
            }
            CustomTab(selectedTabIndex = selectedTabIndex, onTabClick = {
                selectedTabIndex = it
            }, tabItems = trendingItems) {
                val trendingMovies = trendingViewModel.trendingFlow.collectAsLazyPagingItems()
                MovieListCard(trendingMovies, movieItemClick = movieItemClick)

            }
        }
    }


}


