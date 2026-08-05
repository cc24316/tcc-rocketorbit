package com.example.rocketorbittcc.Screens.Perfil



import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.rocketorbittcc.Models.OfensivaManager


@Composable
fun SeloOfensiva(ofensiva: Int) {
    Row(
        modifier = Modifier
            .clip(RoundedCornerShape(20.dp))
            .background(Color(0xFF2B195E))
            .padding(horizontal = 12.dp, vertical = 6.dp)
    ) {
        Text(
            text = "🔥 $ofensiva",
            color = Color(0xFFFFD54F),
            fontWeight = FontWeight.Bold,
            fontSize = 14.sp
        )
    }
}