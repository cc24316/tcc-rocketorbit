package com.example.rocketorbittcc.Screens.Perfil

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.rocketorbittcc.Models.OfensivaManager
import java.util.Calendar

// Total de estrelas necessárias pra completar a constelação da semana
private const val TOTAL_ESTRELAS = 7


@Composable
fun HomeScreenRoot() {
    val context = LocalContext.current
    val prefs = context.getSharedPreferences("rocketorbit", Context.MODE_PRIVATE)

    // Se ainda não tem data salva, ou se hoje é domingo, zera as estrelas
    // (começa uma nova semana de constelação)
    val hoje = Calendar.getInstance().get(Calendar.DAY_OF_WEEK)
    val ultimoReset = prefs.getLong("lastReset", 0)
    val precisaResetar = ultimoReset == 0L || hoje == Calendar.SUNDAY

    val estrelas: Int
    if (precisaResetar) {
        estrelas = 0
        prefs.edit()
            .putInt("stars", 0)
            .putLong("lastReset", System.currentTimeMillis())
            .apply()
    } else {
        estrelas = prefs.getInt("stars", 0)
    }

    val nome = prefs.getString("username", "Você") ?: "Você"
    val diasDeOfensiva = OfensivaManager.getOfensivaAtual(context)

    HomeScreen(
        nome = nome,
        estrelas = estrelas,
        ofensiva = diasDeOfensiva
    )
}

/**
 * Tela inicial (Home). Recebe os dados prontos e só desenha a UI.
 */
@Composable
fun HomeScreen(
    nome: String,
    estrelas: Int,
    ofensiva: Int
) {
    // Quanto da barra de progresso já foi preenchido (0.0 a 1.0)
    val progresso = estrelas.toFloat() / TOTAL_ESTRELAS

    // Quantas estrelas ainda faltam pra completar a semana
    val faltam = (TOTAL_ESTRELAS - estrelas).coerceAtLeast(0)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF0B0B1A))
            .statusBarsPadding()
            .verticalScroll(rememberScrollState())
    ) {


        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.33f)
                .background(Color(0xFF1A1A2E))
                .padding(20.dp)
        ) {

            Column {

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {

                    Row(verticalAlignment = Alignment.CenterVertically) {

                        // Círculo amarelo no lugar da foto de perfil
                        Box(
                            modifier = Modifier
                                .size(90.dp)
                                .clip(CircleShape)
                                .background(Color(0xFFFFD54F))
                        )

                        Spacer(Modifier.width(12.dp))

                        Text(
                            text = nome,
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                    }

                    // Selo de ofensiva, ex: "🔥 5"

                }

                Spacer(Modifier.height(18.dp))

                Text(
                    text = "CONSTELAÇÃO",
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )

                Spacer(Modifier.height(6.dp))

                Text(
                    text = "$estrelas / $TOTAL_ESTRELAS",
                    color = Color(0xFFFFD54F),
                    fontWeight = FontWeight.Bold
                )

                Spacer(Modifier.height(8.dp))

                LinearProgressIndicator(
                    progress = progresso,
                    modifier = Modifier.fillMaxWidth(),
                    color = Color(0xFFFFD54F),
                    trackColor = Color.DarkGray
                )
            }
        }

        Spacer(Modifier.height(16.dp))

        Column(Modifier.padding(16.dp)) {

            // Card da ofensiva
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = Color(0xFF1A1A2E)),
                shape = RoundedCornerShape(16.dp)
            ) {
                Column(Modifier.padding(16.dp)) {

                    Text(
                        text = "OFENSIVA",
                        color = Color(0xFFFFD54F),
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(Modifier.height(8.dp))

                    Text(
                        text = if (ofensiva > 0) {
                            "$ofensiva ${if (ofensiva == 1) "dia seguido" else "dias seguidos"}"
                        } else {
                            " Nenhuma ofensiva ainda"
                        },
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp
                    )

                    Spacer(Modifier.height(4.dp))

                    Text(
                        text = "Conclua uma missão hoje pra manter a sequência",
                        color = Color.Gray,
                        fontSize = 12.sp
                    )
                }
            }

            Spacer(Modifier.height(12.dp))

            // Card da constelação (estrelas que faltam)
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = Color(0xFF1A1A2E)),
                shape = RoundedCornerShape(16.dp)
            ) {
                Column(Modifier.padding(16.dp)) {

                    Text(
                        text = "CONSTELAÇÃO",
                        color = Color(0xFFFFD54F),
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(Modifier.height(8.dp))

                    Text(
                        text = "RESTAM $faltam",
                        color = Color.Cyan,
                        fontSize = 12.sp
                    )
                }
            }

            Spacer(Modifier.height(12.dp))

            // Card de incentivo
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = Color(0xFF1A1A2E)),
                shape = RoundedCornerShape(16.dp)
            ) {
                Column(Modifier.padding(16.dp)) {

                    Text(
                        text = "Mantenha a órbita",
                        color = Color(0xFFFFD54F),
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(Modifier.height(6.dp))

                    Text(
                        text = if (estrelas < TOTAL_ESTRELAS) {
                            "Complete suas missões para encher sua constelação!!!"
                        } else {
                            "7/7! Mandou bem!"
                        },
                        color = Color.Gray,
                        fontSize = 12.sp
                    )
                }
            }
        }
    }
}