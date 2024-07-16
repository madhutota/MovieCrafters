package com.moviecrafters.presentation.components

import android.annotation.SuppressLint
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

@SuppressLint("UseOfNonLambdaOffsetOverload")
@Composable
fun CustomBackdropScaffold(
    appBar: @Composable () -> Unit,
    backLayerContent: @Composable () -> Unit,
    frontLayerContent: @Composable () -> Unit,
    peekHeight: Dp = 64.dp,
    backLayerBackgroundColor: Color = Color.Gray,
    frontLayerBackgroundColor: Color = Color.White
) {
    var isRevealed by remember { mutableStateOf(false) }
    val scaffoldState = rememberCoroutineScope()
    val frontLayerOffset by animateDpAsState(targetValue = if (isRevealed) peekHeight else 0.dp,
        label = ""
    )

    Box(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier
            .fillMaxSize()
            .background(backLayerBackgroundColor)) {
            appBar()
            backLayerContent()
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .offset(y = frontLayerOffset)
                .background(frontLayerBackgroundColor)
                .clickable {
                    scaffoldState.launch {
                        isRevealed = !isRevealed
                    }
                }
        ) {
            frontLayerContent()
        }
    }
}