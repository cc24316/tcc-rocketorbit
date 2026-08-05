package com.example.rocketorbittcc.Screens.Rodape


import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.example.rocketorbittcc.Screens.Perfil.HomeScreenRoot
import com.example.rocketorbittcc.Screens.Tarefas.MissaoScreen

@Composable
fun AplicarRodape() {

    var abaAtual by remember { mutableStateOf(Aba.MISSOES) }

    Scaffold(
        bottomBar = {
            RodapeNavegacao(
                abaAtual = abaAtual,
                onAbaSelecionada = { abaAtual = it }
            )
        }
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            when (abaAtual) {
                Aba.MISSOES -> MissaoScreen()
                Aba.CONSTELACAO -> HomeScreenRoot()//trocar aq
                Aba.PERFIL -> {
                    HomeScreenRoot()
                }
            }
        }
    }
}