package com.moviecrafters.presentation.ppularPeople

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
import com.moviecrafters.presentation.components.PeopleListCard
import com.moviecrafters.presentation.components.peoples
import com.moviecrafters.presentation.home.HomeTabActions
import com.moviecrafters.presentation.home.MovieBottomBar

@Composable
fun People(
    peopleViewModel: PopularPeopleViewModel,
    onNavigateToRoute: (String) -> Unit,
) {
    Scaffold(
        bottomBar = {
            MovieBottomBar(
                tabs = HomeTabActions.entries.toTypedArray(),
                currentRoute = HomeTabActions.POPULAR_PEOPLES.route,
                navigateToRoute = onNavigateToRoute
            )
        }, modifier = Modifier.fillMaxSize()
    ) { paddingValues ->
        Surface(modifier = Modifier.padding(paddingValues)) {
            var selectedTabIndex by remember {
                mutableIntStateOf(0)
            }
            LaunchedEffect(selectedTabIndex) {
                peopleViewModel.onTabSelected("popular")
            }
            CustomTab(selectedTabIndex = selectedTabIndex, onTabClick = {
                selectedTabIndex = it

            }, tabItems = peoples) {
                val trendingPeopleList = peopleViewModel.popularPeoples.collectAsLazyPagingItems()
                PeopleListCard(trendingPeopleList)
            }
        }
    }

}