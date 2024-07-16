package com.moviecrafters

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.moviecrafters.presentation.trending.TrendingViewModel
import com.moviecrafters.ui.theme.MovieCraftersTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    val trendingViewModel by viewModels<TrendingViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()/*.apply {
            setKeepOnScreenCondition{
                trendingViewModel.loading.value
            } }*/
        setContent {
            MovieCraftersTheme {
                MovieCrafterApp()
            }
        }

    }
}
