package com.moviecrafters

import androidx.compose.runtime.Composable
import com.moviecrafters.presentation.navigation.HostNavigation
import com.moviecrafters.ui.theme.MovieCraftersTheme


@Composable
fun MovieCrafterApp() {
    MovieCraftersTheme {
        HostNavigation()
    }
}