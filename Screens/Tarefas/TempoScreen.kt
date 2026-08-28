package com.example.rocketorbittcc.Screens.Tarefas

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
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
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.rocketorbittcc.Models.OfensivaManager
import com.example.rocketorbittcc.Models.Tarefa
import com.example.rocketorbittcc.R
import com.example.rocketorbittcc.Screens.Perfil.SeloOfensiva
import com.example.rocketorbittcc.Screens.fundoEstrela
import kotlinx.coroutines.delay
import kotlin.math.cos
import kotlin.math.sin
import com.example.orbitrockettcc.Screens.Perfil.adicionarEstrela

private fun duracaoParaSegundos(duracao: String): Int {
    val minutos = duracao.toIntOrNull() ?: 25
    return minutos * 60
}

@Composable
fun TempoScreen(
    tarefa: Tarefa,
    onVoltar: () -> Unit = {}
) {
    val context = LocalContext.current

    var tempoRestante by remember(tarefa) { mutableStateOf(duracaoParaSegundos(tarefa.duracao)) }
    var rodando by remember { mutableStateOf(false) }
    var missaoConcluida by remember(tarefa) { mutableStateOf(false) }
    val ofensiva = OfensivaManager.getOfensivaAtual(context)

    LaunchedEffect(rodando, tarefa) {
        while (rodando && tempoRestante > 0) {
            delay(1000L)
            tempoRestante--
        }
        if (tempoRestante == 0) {
            rodando = false
            if (!missaoConcluida) {
                missaoConcluida = true
                OfensivaManager.registrarConclusao(context)
                adicionarEstrela(context)
            }
        }
    }

    val minutos = tempoRestante / 60
    val segundos = tempoRestante % 60
    val tempoFormatado = "%02d:%02d".format(minutos, segundos)

    Box(modifier = Modifier.fillMaxSize()) {

        fundoEstrela()

        Icon(
            imageVector = Icons.Default.ArrowBack,
            contentDescription = "Voltar",
            tint = Color.White,
            modifier = Modifier
                .padding(top = 50.dp, start = 20.dp)
                .clickable { onVoltar() }
        )

        Box(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(top = 50.dp, end = 20.dp)
        ) {
            SeloOfensiva(ofensiva)
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 70.dp, bottom = 50.dp, start = 24.dp, end = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {

            Text(
                text = "Persista na sua missâo!",
                fontSize = 26.sp,
                color = Color.White,
                fontWeight = FontWeight.SemiBold,
                textAlign = TextAlign.Center
            )

            Box(
                modifier = Modifier.size(260.dp),
                contentAlignment = Alignment.Center
            ) {
                Canvas(modifier = Modifier.fillMaxSize()) {
                    val raio = size.minDimension / 2
                    val centro = Offset(size.width / 2, size.height / 2)

                    drawCircle(
                        color = Color(0xFFF8F6F6).copy(alpha = 0.6f),
                        radius = raio,
                        center = centro,
                        style = Stroke(width = 2.dp.toPx())
                    )

                    fun pontoNaOrbita(anguloGraus: Float): Offset {
                        val rad = Math.toRadians(anguloGraus.toDouble())
                        return Offset(
                            x = centro.x + raio * cos(rad).toFloat(),
                            y = centro.y + raio * sin(rad).toFloat()
                        )
                    }

                    drawCircle(Color.White, radius = 5.dp.toPx(), center = pontoNaOrbita(-90f))
                    drawCircle(Color.White, radius = 4.dp.toPx(), center = pontoNaOrbita(0f))
                    drawCircle(Color(0xFFFFFFFF), radius = 4.dp.toPx(), center = pontoNaOrbita(200f))
                }

                Image(
                    painter = painterResource(R.drawable.fogo),
                    contentDescription = null,
                    modifier = Modifier
                        .size(75.dp)
                        .offset(x = (-50).dp, y = 55.dp)
                        .rotate(-140f)
                )
                Image(
                    painter = painterResource(R.drawable.foguete),
                    contentDescription = null,
                    modifier = Modifier
                        .size(220.dp)
                        .offset(x = 3.dp, y = (-25).dp)
                )
                Image(
                    painter = painterResource(R.drawable.brilho),
                    contentDescription = null,
                    modifier = Modifier
                        .size(280.dp)
                        .offset(x = (-2).dp, y = (-25).dp)
                        .alpha(0.7f)
                )
            }

            Column(horizontalAlignment = Alignment.CenterHorizontally) {

                Text(
                    text = "Indo para ${tarefa.planeta}",
                    fontSize = 18.sp,
                    color = Color(0xFFFFD54F),
                    fontWeight = FontWeight.SemiBold,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(8.dp))
    
                Text(
                    text = tarefa.nome,
                    fontSize = 26.sp,
                    color = Color.White,
                    fontWeight = FontWeight.SemiBold,
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(50.dp))

                if (missaoConcluida) {
                    Text(
                        text = "Missão concluída! Ofensiva atualizada ⭐",
                        color = Color(0xFFFFD54F),
                        fontSize = 14.sp,
                        fontWeight = FontWeight.SemiBold,
                        textAlign = TextAlign.Center
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                }

                Text(
                    text = "Tempo de foco",
                    color = Color.White.copy(alpha = 0.7f),
                    fontSize = 14.sp
                )

                Text(
                    text = tempoFormatado,
                    color = Color.White,
                    fontSize = 42.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(20.dp))

                Button(
                    onClick = {
                        if (tempoRestante == 0) {
                            tempoRestante = duracaoParaSegundos(tarefa.duracao)
                            missaoConcluida = false
                        }
                        rodando = !rodando
                    },
                    modifier = Modifier
                        .fillMaxWidth(0.75f)
                        .height(52.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF4D3373)
                    )
                ) {
                    Text(
                        text = when {
                            tempoRestante == 0 -> "Reiniciar"
                            rodando -> "Pausar"
                            else -> "Iniciar Foco"
                        },
                        color = Color.White,
                        fontSize = 16.sp
                    )
                }
            }
        }
    }
}
