package com.moviecrafters.presentation.upcoming

import android.util.Log
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
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
import com.moviecrafters.presentation.components.moviesList
import com.moviecrafters.presentation.home.HomeTabActions
import com.moviecrafters.presentation.home.MovieBottomBar

@Composable
fun MoviesList(
    upcomingMoviesViewModel: UpcomingMoviesViewModel,
    onNavigateToRoute: (String) -> Unit,
    movieItemClick: (Trending) -> Unit,
) {
    Scaffold(
        bottomBar = {
            MovieBottomBar(
                tabs = HomeTabActions.entries.toTypedArray(),
                currentRoute = HomeTabActions.MOVIES.route,
                navigateToRoute = onNavigateToRoute
            )
        }, modifier = Modifier.fillMaxSize()
    ) { paddingValues ->
        Surface(modifier = Modifier.padding(paddingValues)) {
            var selectedTabIndex by remember {
                mutableIntStateOf(0)
            }
            val movieCategories = listOf("now_playing", "popular", "top_rated", "upcoming")
            LaunchedEffect(selectedTabIndex) {
                // Fetch initial data when Composable is first loaded
                val initialQuery = movieCategories.getOrElse(selectedTabIndex) { "now_playing" }
                upcomingMoviesViewModel.onTabSelected(initialQuery)
            }


            CustomTab(selectedTabIndex = selectedTabIndex, onTabClick = {
                selectedTabIndex = it

            }, tabItems = moviesList) {
                val trendingMovies = upcomingMoviesViewModel.upcomingFlow.collectAsLazyPagingItems()
                Log.e("UpcomingMoviesViewModel", "" + trendingMovies.itemCount)
                MovieListCard(trendingMovies, movieItemClick = movieItemClick)

            }
        }
    }

}