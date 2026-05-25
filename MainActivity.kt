package com.example.tcc

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.example.tcc.Screens.CadastroScreen
import com.example.tcc.Screens.HomeScreen
import com.example.tcc.Screens.LoginScreen

import com.example.tcc.Screens.TeladeIncio

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {

            var telaAtual by remember {
                mutableStateOf("home")
            }

            when (telaAtual) {

                "home" -> HomeScreen(
                    onFinish = { telaAtual = "inicio" }
                )

                "inicio" -> TeladeIncio(//chama funcao
                    onFinish = { telaAtual = "login" }
                )

                "login" -> {
                    LoginScreen(
                        onFinish = { telaAtual = "dashboard" },
                        onCadastro = { telaAtual = "cadastro" }
                    )
                }

                "cadastro" -> {
                    CadastroScreen(
                        onCadastroSucesso = { telaAtual = "login" },
                        onVoltarLogin = { telaAtual = "login" }
                    )
                }
            }
        }
    }
}