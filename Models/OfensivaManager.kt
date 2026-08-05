package com.example.rocketorbittcc.Models

import android.content.Context
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale


object OfensivaManager {

    private const val PREFS = "rocketorbit"
    private const val KEY_OFENSIVA = "ofensiva"
    private const val KEY_ULTIMO_DIA = "ultimo_dia"

    private val formato = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())

    private fun hoje() = formato.format(Date())

    private fun ontem(): String {
        val cal = Calendar.getInstance()
        cal.add(Calendar.DAY_OF_YEAR, -1)
        return formato.format(cal.time)
    }


    fun getOfensivaAtual(context: Context): Int {
        val prefs = context.getSharedPreferences(PREFS, Context.MODE_PRIVATE)
        return prefs.getInt(KEY_OFENSIVA, 0)
    }


    fun registrarConclusao(context: Context): Int {
        val prefs = context.getSharedPreferences(PREFS, Context.MODE_PRIVATE)
        val ultimoDia = prefs.getString(KEY_ULTIMO_DIA, "")

        val nova = when (ultimoDia) {
            hoje() -> getOfensivaAtual(context)       // já contou hoje
            ontem() -> getOfensivaAtual(context) + 1  // continua a sequência
            else -> 1                                 // pulou um dia, recomeça
        }

        prefs.edit()
            .putInt(KEY_OFENSIVA, nova)
            .putString(KEY_ULTIMO_DIA, hoje())
            .apply()

        return nova
    }
}