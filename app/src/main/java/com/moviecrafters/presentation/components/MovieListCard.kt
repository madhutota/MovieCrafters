package com.moviecrafters.presentation.components

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.moviecrafters.domain.model.Trending
import kotlinx.coroutines.flow.Flow
import com.moviecrafters.presentation.util.items
import com.moviecrafters.ui.theme.purple


@RequiresApi(Build.VERSION_CODES.S)
@Composable
fun MovieListCard(
    trendingMovies: LazyPagingItems<Trending>, movieItemClick: (Trending) -> Unit

) {
    // val moviesItems: LazyPagingItems<Trending> = trendingMovies.collectAsLazyPagingItems()
    Column {
        CustomLoader()
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier
                .background(purple)
                .padding(start = 5.dp, end = 5.dp),
            content = {
                items(trendingMovies) { item ->
                    item?.let {
                        MovieItemCard(modifier = Modifier, it, movieItemClick = movieItemClick)
                    }
                }


            }
        )
    }

}