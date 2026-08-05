package com.example.rocketorbittcc.Screens.Rodape

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Rocket
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

enum class Aba {
    MISSOES, CONSTELACAO, PERFIL
}


@Composable
fun RodapeNavegacao(
    abaAtual: Aba,
    onAbaSelecionada: (Aba) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFF2B195E))
            .padding(vertical = 12.dp),
        horizontalArrangement = Arrangement.SpaceAround
    ) {



        ItemRodape(
            icone = Icons.Default.Rocket,
            label = "Missões",
            selecionado = abaAtual == Aba.MISSOES,
            onClick = { onAbaSelecionada(Aba.MISSOES) }
        )

        ItemRodape(
            icone = Icons.Default.Star,
            label = "Constelação",
            selecionado = abaAtual == Aba.CONSTELACAO,
            onClick = { onAbaSelecionada(Aba.CONSTELACAO) }
        )

        ItemRodape(
            icone = Icons.Default.Person,
            label = "Perfil",
            selecionado = abaAtual == Aba.PERFIL,
            onClick = { onAbaSelecionada(Aba.PERFIL) }
        )
    }
}

@Composable
private fun ItemRodape(
    icone: ImageVector,
    label: String,
    selecionado: Boolean,
    onClick: () -> Unit
) {
    val cor = if (selecionado) Color.White else Color(0xFF8A7FC7)

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.selectable(
            selected = selecionado,
            onClick = onClick
        )
    ) {
        Icon(
            imageVector = icone,
            contentDescription = label,
            tint = cor
        )
        Text(
            text = label,
            color = cor,
            fontSize = 11.sp
        )
    }
}