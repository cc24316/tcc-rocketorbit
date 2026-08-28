package com.example.rocketorbittcc.Screens.Tarefas

import com.example.rocketorbittcc.Models.Tarefa
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.rocketorbittcc.Screens.fundoEstrela

@Composable
fun MissaoScreen() {

    var mostrarCard by remember { mutableStateOf(false) }
    var nomeTarefa by remember { mutableStateOf("") }
    var duracao by remember { mutableStateOf("") }
    var tarefaSelecionada by remember { mutableStateOf<Tarefa?>(null) }
    val tarefas = remember { mutableStateListOf<Tarefa>() }

    fun fecharFormulario() {
        nomeTarefa = ""
        duracao = ""
        mostrarCard = false
    }

    val tarefaAtual = tarefaSelecionada
    if (tarefaAtual != null) {
        TempoScreen(
            tarefa = tarefaAtual,
            onVoltar = { tarefaSelecionada = null }
        )
        return
    }

    Box(modifier = Modifier.fillMaxSize()) {

        fundoEstrela()

        Text(
            text = "Minhas Missões",
            fontSize = 30.sp,
            color = Color.White,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.offset(x = 30.dp, y = 50.dp)
        )

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 100.dp, start = 16.dp, end = 16.dp)
        ) {
            items(tarefas) { tarefa ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(120.dp)
                        .padding(vertical = 8.dp)
                        .clickable { tarefaSelecionada = tarefa },
                    colors = CardDefaults.cardColors(containerColor = Color(0xFF2B195E))
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = tarefa.nome,
                                color = Color.White,
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 20.sp
                            )
                            Text(
                                text = "Duração: ${tarefa.duracao} minutos",
                                color = Color.White
                            )
                        }

                        Button(onClick = { tarefas.remove(tarefa) }) {
                            Text("Excluir")
                        }
                    }
                }
            }
        }

        if (mostrarCard) {
            Card(
                modifier = Modifier
                    .align(Alignment.Center)
                    .padding(16.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFF36266C))
            ) {
                Column(modifier = Modifier.padding(16.dp)) {

                    Text(
                        text = "Nova Missão",
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    OutlinedTextField(
                        value = nomeTarefa,
                        onValueChange = { nomeTarefa = it },
                        label = { Text(text = "Nome da missão", color = Color.White) },
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedTextColor = Color.White,
                            unfocusedTextColor = Color.White,
                            focusedLabelColor = Color.White,
                            unfocusedLabelColor = Color.White,
                            cursorColor = Color.White,
                            focusedBorderColor = Color.White,
                            unfocusedBorderColor = Color.White
                        )
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(text = "Duração", color = Color.White)

                    Spacer(modifier = Modifier.height(4.dp))

                    Row {
                        OutlinedButton(onClick = { duracao = "1" }) {
                            Text("1min", color = Color.White)
                        }

                        Spacer(modifier = Modifier.width(4.dp))

                        OutlinedButton(onClick = { duracao = "30" }) {
                            Text("30min", color = Color.White)
                        }

                        Spacer(modifier = Modifier.width(4.dp))

                        OutlinedButton(onClick = { duracao = "60" }) {
                            Text("1h", color = Color.White)
                        }

                        Spacer(modifier = Modifier.width(4.dp))

                        OutlinedButton(onClick = { duracao = "120" }) {
                            Text("2h", color = Color.White)
                        }
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = if (duracao.isBlank()) {
                            "Nenhuma duração escolhida"
                        } else {
                            "Selecionado: $duracao minutos"
                        },
                        color = Color.White
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Row {
                        Button(
                            onClick = {
                                if (nomeTarefa.isNotBlank() && duracao.isNotBlank()) {
                                    tarefas.add(
                                        Tarefa(
                                            nome = nomeTarefa,
                                            duracao = duracao,
                                            planeta = planetaSelecionado
                                        )
                                    )
                                    fecharFormulario()
                                }
                            }
                        ) {
                            Text("Salvar", color = Color.White)
                        }

                        Spacer(modifier = Modifier.width(8.dp))

                        OutlinedButton(onClick = { fecharFormulario() }) {
                            Text("Cancelar", color = Color.White)
                        }
                    }
                }
            }
        }

        FloatingActionButton(
            onClick = { mostrarCard = true },
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(16.dp)
                .offset(y = 25.dp),
            containerColor = Color(0xFF2B195E)
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = "Adicionar",
                tint = Color.White
            )
        }
    }
}
