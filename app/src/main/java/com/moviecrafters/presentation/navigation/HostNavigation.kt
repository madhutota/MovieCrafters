package com.moviecrafters.presentation.navigation

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.toRoute
import com.moviecrafters.domain.model.Trending
import com.moviecrafters.presentation.detail.MovieDetailScreen
import com.moviecrafters.presentation.upcoming.MoviesList
import com.moviecrafters.presentation.ppularPeople.People
import com.moviecrafters.presentation.tvSeries.TVSeries
import com.moviecrafters.presentation.trending.Trending
import com.moviecrafters.presentation.home.HomeTabActions
import com.moviecrafters.presentation.ppularPeople.PopularPeopleViewModel
import com.moviecrafters.presentation.trending.TrendingViewModel
import com.moviecrafters.presentation.tvSeries.TvSeriesViewModel
import com.moviecrafters.presentation.upcoming.UpcomingMoviesViewModel
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

@Composable
fun HostNavigation() {
    val movieCrafterNavController = rememberMovieCrafterskNavController()
    NavHost(
        navController = movieCrafterNavController.navController,
        startDestination = HomeTabActions.TRENDING.route
    ) {
        val route = movieCrafterNavController::navigateToBottomBarRoute
        composable(HomeTabActions.TRENDING.route) { from ->
            val trendingViewModel = hiltViewModel<TrendingViewModel>()
            Trending(trendingViewModel, route) {

                val trendingJson = Json.encodeToString(it)

                it.posterPath?.let { imageUrl ->
                    movieCrafterNavController.navController.navigate(Screen.MovieDetail(imageUrl))
                }

            }
        }
        composable(HomeTabActions.MOVIES.route) { from ->
            val upcomingMoviesViewModel = hiltViewModel<UpcomingMoviesViewModel>()

            MoviesList(upcomingMoviesViewModel, route) {

            }
        }
        composable(HomeTabActions.SERIES.route) { from ->
            val tvSeriesViewModel = hiltViewModel<TvSeriesViewModel>()
            TVSeries(tvSeriesViewModel, route) {

            }
        }
        composable(HomeTabActions.POPULAR_PEOPLES.route) { from ->
            val popularPeopleViewModel = hiltViewModel<PopularPeopleViewModel>()
            People(popularPeopleViewModel, route)
        }
        /*composable(
            "movieDetail/{trending}",
            arguments = listOf(navArgument("trending") {
                type = SerializableNavType(Trending::class)
            })
        ) { backStackEntry ->
            *//*val trending = backStackEntry.toRoute<Trending>()
            MovieDetailScreen(trending)*//*
        }*/

        composable<Screen.MovieDetail> {
            val id = it.toRoute<Screen.MovieDetail>()
            Log.e("Navigation", "---- $id")
            MovieDetailScreen(id.imageUrl)

        }


    }
}