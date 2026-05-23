package com.example.tcc

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.layout.statusBarsPadding
import java.util.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val prefs = getSharedPreferences("rocketorbit", MODE_PRIVATE)

        val stars = prefs.getInt("stars", 0)
        val lastReset = prefs.getLong("lastReset", 0)

        val today = Calendar.getInstance().get(Calendar.DAY_OF_WEEK)

        val reset = lastReset == 0L || today == Calendar.SUNDAY

        val finalStars = if (reset) {
            prefs.edit().putInt("stars", 0).apply()
            prefs.edit().putLong("lastReset", System.currentTimeMillis()).apply()
            0
        } else {
            stars
        }

        val name = prefs.getString("username", "Você") ?: "Você"

        setContent {
            Screen(name, finalStars)
        }
    }
}

@Composable
fun Screen(name: String, stars: Int) {

    val total = 7
    val progress = stars.toFloat() / total
    val remaining = (total - stars).coerceAtLeast(0)

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

                Row(verticalAlignment = Alignment.CenterVertically) {

                    Box(
                        modifier = Modifier
                            .size(90.dp)
                            .clip(CircleShape)
                            .background(Color(0xFFFFD54F)),
                        contentAlignment = Alignment.Center
                    ) {}
                    Spacer(Modifier.width(12.dp))

                    Column {
                        Text(
                            name,
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                    }
                }

                Spacer(Modifier.height(18.dp))

                Text(
                    "CONSTELAÇÃO",
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )

                Spacer(Modifier.height(6.dp))

                Text(
                    "$stars / $total",
                    color = Color(0xFFFFD54F),
                    fontWeight = FontWeight.Bold
                )

                Spacer(Modifier.height(8.dp))

                LinearProgressIndicator(
                    progress = progress,
                    modifier = Modifier.fillMaxWidth(),
                    color = Color(0xFFFFD54F),
                    trackColor = Color.DarkGray
                )
            }
        }

        Spacer(Modifier.height(16.dp))

        Column(Modifier.padding(16.dp)) {

            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = Color(0xFF1A1A2E)),
                shape = RoundedCornerShape(16.dp)
            ) {

                Column(Modifier.padding(16.dp)) {

                    Text(
                        "CONSTELAÇÃO",
                        color = Color(0xFFFFD54F),
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(Modifier.height(8.dp))
                    Spacer(Modifier.height(4.dp))

                    Text(
                        "RESTAM $remaining",
                        color = Color.Cyan,
                        fontSize = 12.sp
                    )
                }
            }

            Spacer(Modifier.height(12.dp))

            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = Color(0xFF1A1A2E)),
                shape = RoundedCornerShape(16.dp)
            ) {

                Column(Modifier.padding(16.dp)) {

                    Text(
                        "Mantenha a órbita",
                        color = Color(0xFFFFD54F),
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(Modifier.height(6.dp))

                    Text(
                        if (stars < 7)
                            "Complete suas missões para encher sua constelação!!!"
                        else
                            "7/7! Mandou bem!",
                        color = Color.Gray,
                        fontSize = 12.sp
                    )
                }
            }
        }
    }
}
