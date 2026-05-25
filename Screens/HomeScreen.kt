package com.example.tcc.Screens

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.tcc.R

import kotlinx.coroutines.delay

@Composable
fun HomeScreen(onFinish: () -> Unit) {

    var animacao by remember {
        mutableStateOf(false)
    }

    LaunchedEffect(Unit) {

        animacao = true

        delay(4000)

        onFinish()
    }

    val animacaoInfinita =
        rememberInfiniteTransition(label = "")

    val entrada by animateFloatAsState(
        if (animacao) 0f else 300f,
        tween(1800, easing = FastOutSlowInEasing),
        label = ""
    )

    val alpha by animateFloatAsState(
        if (animacao) 1f else 0f,
        tween(1500),
        label = ""
    )

    val fogueteY by animacaoInfinita.animateFloat(
        -8f,
        8f,
        infiniteRepeatable(
            tween(1200, easing = LinearEasing),
            RepeatMode.Reverse
        ),
        label = ""
    )

    val fogoY by animacaoInfinita.animateFloat(
        -1f,
        2f,
        infiniteRepeatable(
            tween(1400, easing = LinearEasing),
            RepeatMode.Reverse
        ),
        label = ""
    )

    val fogoScale by animacaoInfinita.animateFloat(
        1f,
        1.06f,
        infiniteRepeatable(
            tween(900, easing = LinearEasing),
            RepeatMode.Reverse
        ),
        label = ""
    )

    val brilho by animacaoInfinita.animateFloat(
        1f,
        1.08f,
        infiniteRepeatable(
            tween(1800, easing = LinearEasing),
            RepeatMode.Reverse
        ),
        label = ""
    )

    Box(
        modifier = Modifier.fillMaxSize()
    ) {

        fundoEstrela()

        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {


            Image(
                painter = painterResource(R.drawable.fogo),
                contentDescription = null,
                modifier = Modifier
                    .size(75.dp)
                    .offset(
                        x = (-50).dp,
                        y = (
                                80 +
                                        fogueteY +
                                        fogoY +
                                        entrada
                                ).dp
                    )
                    .rotate(-140f)
                    .scale(fogoScale)
                    .alpha(0.85f * alpha)
            )


            Image(
                painter = painterResource(R.drawable.foguete),
                contentDescription = null,
                modifier = Modifier
                    .size(220.dp)
                    .offset(
                        y = (
                                fogueteY +
                                        entrada
                                ).dp
                    )
                    .alpha(alpha)
            )


            Image(
                painter = painterResource(R.drawable.brilho),
                contentDescription = null,
                modifier = Modifier
                    .size(280.dp)
                    .offset(
                        y = (
                                fogueteY +
                                        entrada
                                ).dp
                    )
                    .scale(brilho)
                    .alpha(0.7f * alpha)
            )
        }
    }
}

