package com.moviecrafters.presentation.components

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment

import android.graphics.RenderEffect
import android.graphics.Shader
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asComposeRenderEffect
import androidx.compose.ui.graphics.graphicsLayer


@RequiresApi(Build.VERSION_CODES.S)
@Composable
fun LoaderEntity(
    modifier: Modifier = Modifier,
    blur: Float = 30f,
    metaContent: @Composable BoxScope.() -> Unit,
    content: @Composable BoxScope.() -> Unit = {},
) {

    Box(
        modifier
            .width(IntrinsicSize.Min)
            .height(IntrinsicSize.Min),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .customBlur(blur),
            content = metaContent,
        )
        content()
    }
}


@SuppressLint("SuspiciousModifierThen")
@RequiresApi(Build.VERSION_CODES.S)
@Composable
fun Modifier.customBlur(blur: Float): Modifier {
    // Early return for zero blur
    if (blur <= 0f) return this

    // Cache the RenderEffect
    val renderEffect = remember(blur) {
        RenderEffect.createBlurEffect(
            blur,
            blur,
            Shader.TileMode.DECAL
        ).asComposeRenderEffect()
    }

    return this.then(
        graphicsLayer {
            this.renderEffect = renderEffect
        }
    )
}