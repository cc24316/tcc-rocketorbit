package com.example.orbitrockettcc

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.*
import com.example.orbitrockettcc.Screens.Logar.CadastroScreen
import com.example.orbitrockettcc.Screens.Logar.LoginScreen
import com.example.orbitrockettcc.Screens.Rodape.AplicarRodape
import com.example.orbitrockettcc.ui.theme.OrbitRocketTCCTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {

            OrbitRocketTCCTheme {

                var telaAtual by remember {
                    mutableStateOf("login")
                }

                when (telaAtual) {

                    "login" -> LoginScreen(
                        onFinish = {
                            telaAtual = "principal"
                        },
                        onCadastro = {
                            telaAtual = "cadastro"
                        }
                    )


                    "cadastro" -> CadastroScreen(
                        onCadastroSucesso = {
                            telaAtual = "login"
                        },
                        onVoltarLogin = {
                            telaAtual = "login"
                        }
                    )


                    "principal" -> {

                        AplicarRodape()

                    }
                }
            }
        }
    }
}
