package com.example.tcc.Screens

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlin.math.sin


@Composable
fun fundoEstrela() {

    val infiniteTransition =
        rememberInfiniteTransition(label = "")

    val stars = remember {
        List(120) {
            Triple(
                Math.random().toFloat(),
                Math.random().toFloat(),
                Math.random().toFloat()
            )
        }
    }

    val time by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                3000,
                easing = LinearEasing
            ),
            repeatMode = RepeatMode.Restart
        ),
        label = ""
    )

    Canvas(
        modifier = Modifier.fillMaxSize()
    ) {

        drawRect(
            color = Color(0xFF1a0a3e)
        )

        stars.forEach { (x, y, phase) ->

            val alpha =
                (
                        (
                                sin(
                                    (
                                            (time + phase) *
                                                    2 *
                                                    Math.PI
                                            ).toDouble()
                                ) + 1
                                ) / 2
                        )
                    .toFloat()
                    .coerceIn(0.2f, 1f)

            val radius =
                (phase * 1.5f + 0.8f)

            drawCircle(
                color = Color.White.copy(
                    alpha = alpha * 0.85f
                ),
                radius = radius.dp.toPx(),
                center = Offset(
                    x = x * size.width,
                    y = y * size.height
                )
            )
        }
    }
}