package com.example.tcc

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import kotlin.math.sin

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {

            var telaAtual by remember {
                mutableStateOf("splash")
            }

            when (telaAtual) {

                "splash" -> {
                    SplashScreen(
                        onFinish = {
                            telaAtual = "home"
                        }
                    )
                }

                "home" -> {
                    TeladeIncio(
                        onFinish = {
                            telaAtual = "login"
                        }
                    )
                }

                "login" -> {
                    paginaLongin()
                }
            }
        }
    }
}









@Composable
fun SplashScreen(onFinish: () -> Unit) {

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

        fundoDeEstrelas()

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


@Composable
fun TeladeIncio(onFinish: () -> Unit) {

    Box(
        modifier = Modifier.fillMaxSize()
    ) {

        fundoDeEstrelas()

        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {

            Text(
                text = "RocketOrbit",
                fontSize = 50.sp,
                color = Color.Yellow,
                fontWeight = FontWeight.Bold

            )

            LaunchedEffect(Unit) {
                delay(3000)
                onFinish()
            }


        }
    }
}

@Composable
fun paginaLongin() {

    Box(
        modifier = Modifier.fillMaxSize()
    ) {

        fundoDeEstrelas()

        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {


            Image(
                painter = painterResource(R.drawable.fogo),
                contentDescription = null,
                modifier = Modifier
                    .size(75.dp)
                    .offset(
                        x = (-50).dp,
                        y = 80.dp
                    )
                    .rotate(-140f)
            )

            Image(
                painter = painterResource(R.drawable.foguete),
                contentDescription = null,
                modifier = Modifier
                    .size(220.dp)
                    .offset(
                        x = (2).dp,
                        y = -150.dp

                    )


            )

            Image(
                painter = painterResource(R.drawable.brilho),
                contentDescription = null,
                modifier = Modifier
                    .size(280.dp)
                    .offset(
                        x = -6.dp,
                        y = -380.dp
                    )
                    .alpha(0.7f)
            )

            Spacer(modifier = Modifier.height(20.dp))



            Button(
                onClick = {

                }
            ) {
                Text("Entrar")
            }






        }
    }
}


//fundo
@Composable
fun fundoDeEstrelas() {

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
