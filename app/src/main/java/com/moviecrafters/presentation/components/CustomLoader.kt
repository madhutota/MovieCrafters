package com.moviecrafters.presentation.components

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp


@Composable
fun CustomLoader(
    color: Color = Color(0xFF0951E2)
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {

        val x = 9

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            LoaderEntity(modifier = Modifier.fillMaxSize(), blur = 0f, metaContent = {
                val animation = rememberInfiniteTransition(label = "")
                val rotation by animation.animateFloat(
                    initialValue = 0f, targetValue = -360f, animationSpec = infiniteRepeatable(
                        tween(8000, easing = LinearEasing),
                        RepeatMode.Restart,
                    ), label = ""
                )

                Box(
                    modifier = Modifier.size(300.dp),
                    contentAlignment = Alignment.Center,
                ) {
                    Box(modifier = Modifier
                        .rotate(rotation)
                        .drawBehind {
                            drawArc(
                                color = Color.Black,
                                startAngle = 0f,
                                sweepAngle = 215f,
                                useCenter = false,
                                style = Stroke(
                                    width = size.width * .195f, cap = StrokeCap.Round
                                )
                            )
                        }
                        .size(250.dp))
                    for (i in 0..x) {
                        Circle(i * (360f / x))
                    }
                }
            }) {

            }
        } else {
            Text(text = "Loading...", color = color)
        }

    }
}

@Composable
fun Circle(offset: Float) {
    val animation = rememberInfiniteTransition(label = "")
    val rotation by animation.animateFloat(
        initialValue = 0f, targetValue = 360f, animationSpec = infiniteRepeatable(
            tween(8000, easing = LinearEasing),
            RepeatMode.Restart,
        ), label = ""
    )

    Box(
        modifier = Modifier
            .rotate(offset)
            .rotate(rotation)
            .width(300.dp)
    ) {
        Box(
            modifier = Modifier
                .background(Color.Black, CircleShape)
                .size(50.dp)
        )
    }
}