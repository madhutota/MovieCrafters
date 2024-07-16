package com.moviecrafters.presentation.components

import android.annotation.SuppressLint
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.TabPosition
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.moviecrafters.ui.theme.MovieCraftersTyphy
import com.moviecrafters.ui.theme.orangeOne
import com.moviecrafters.ui.theme.purple

@Composable
fun CustomTab(
    modifier: Modifier = Modifier,
    selectedTabIndex: Int,
    onTabClick: (Int) -> Unit,
    tabItems: List<TabItem>,
    content: @Composable BoxScope.() -> Unit

) {
    var textWidths by remember { mutableStateOf(emptyList<Float>()) }

    val pagerState = rememberPagerState(
        initialPage = 0
    ) {
        tabItems.size
    }

    LaunchedEffect(key1 = selectedTabIndex) {
        pagerState.animateScrollToPage(selectedTabIndex)

    }

    LaunchedEffect(pagerState.currentPage, pagerState.isScrollInProgress) {
        if (!pagerState.isScrollInProgress) {
            onTabClick(pagerState.currentPage)
        }
    }
    Column(modifier = modifier) {
        ScrollableTabRow(selectedTabIndex = selectedTabIndex,
            edgePadding = 0.dp,
            divider = {},
            contentColor = orangeOne,
            containerColor = purple,
            indicator = { tabPositions ->
                if (textWidths.isNotEmpty()) {
                    val currentTabWidth = textWidths[selectedTabIndex]
                    Box(
                        Modifier
                            .tabIndicatorOffset(tabPositions[selectedTabIndex])
                            .height(4.dp)
                            .background(color = orangeOne, shape = RoundedCornerShape(8.dp))
                    )
                }
            }


        ) {
            tabItems.forEachIndexed { selectedIndex, item ->
                Tab(selected = selectedIndex == selectedTabIndex, onClick = {
                    onTabClick(selectedIndex)
                }, text = {
                    Text(text = item.title,
                        style = MovieCraftersTyphy.titleMedium,
                        onTextLayout = { textLayoutResult: TextLayoutResult ->
                            if (textWidths.size < tabItems.size) {
                                textWidths =
                                    textWidths + listOf(textLayoutResult.size.width.toFloat())
                            }
                        })
                })
            }

        }

        HorizontalPager(
            state = pagerState, modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) { index ->
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                content()
            }


        }
    }


}


@SuppressLint("UseOfNonLambdaOffsetOverload")
fun Modifier.ownTabIndicatorOffset(
    currentTabPosition: TabPosition, currentTabWidth: Dp = currentTabPosition.width
): Modifier = composed {
    val indicatorOffset by animateDpAsState(
        targetValue = currentTabPosition.left,
        animationSpec = tween(durationMillis = 250, easing = FastOutSlowInEasing),
        label = ""
    )
    fillMaxWidth()
        .wrapContentSize(Alignment.BottomStart)
        .offset(x = indicatorOffset + ((currentTabPosition.width - currentTabWidth) / 2))
        .width(currentTabWidth)
}


data class TabItem(
    var title: String
)

val trendingItems = listOf(
    TabItem("Today"), TabItem("This Week")
)
val moviesList = listOf(
    TabItem("Now Playing"),
    TabItem("Popular"),
    TabItem("Top Rated"),
    TabItem("Upcoming"),
)
val tvSeriesList = listOf(
    TabItem("Airing Today"), TabItem("On The Air"), TabItem("Popular"), TabItem("Top Rated")
)
val peoples = listOf(
    TabItem("Popular")
)