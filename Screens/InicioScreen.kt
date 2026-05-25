package com.example.tcc.Screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay

@Composable
fun TeladeIncio(onFinish: () -> Unit) {

    Box(
        modifier = Modifier.fillMaxSize()
    ) {

        fundoEstrela()

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