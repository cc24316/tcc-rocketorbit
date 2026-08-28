package com.example.rocketorbittcc.Models

data class Tarefa(
    val nome: String,
    val duracao: String
    val planeta: String
) {
    companion object {

        fun focoLivre(duracao: String = "25 minutos"): Tarefa {
            return Tarefa(
                nome = "Foco livre",
                duracao = duracao

            )
        }
    }
}
