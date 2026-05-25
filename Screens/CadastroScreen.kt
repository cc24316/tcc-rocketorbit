package com.example.tcc.Screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tcc.API.RetrofitClient
import com.example.tcc.API.UsuarioRequest
import com.example.tcc.R
import kotlinx.coroutines.launch

@Composable
fun CadastroScreen(
    onCadastroSucesso: () -> Unit,
    onVoltarLogin: () -> Unit
) {

    Box(modifier = Modifier.fillMaxSize()) {

        fundoEstrela()


        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(R.drawable.fogo),
                contentDescription = null,
                modifier = Modifier
                    .size(75.dp)
                    .offset(x = (-50).dp, y = 135.dp)
                    .rotate(-140f)
            )
            Image(
                painter = painterResource(R.drawable.foguete),
                contentDescription = null,
                modifier = Modifier
                    .size(220.dp)
                    .offset(x = 3.dp, y = (-100).dp)
            )
            Image(
                painter = painterResource(R.drawable.brilho),
                contentDescription = null,
                modifier = Modifier
                    .size(280.dp)
                    .offset(x = (-2).dp, y = (-325).dp)
                    .alpha(0.7f)
            )
        }


        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(40.dp)
                .offset(x = 20.dp, y = 350.dp)
        ) {

            var Nome by remember { mutableStateOf("") }
            var Email by remember { mutableStateOf("") }
            var Senha by remember { mutableStateOf("") }
            var ConfirmarSenha by remember { mutableStateOf("") }
            var mensagemErro by remember { mutableStateOf("") }
            val coroutineScope = rememberCoroutineScope()

            OutlinedTextField(
                value = Nome,
                onValueChange = { Nome = it },
                label = { Text("Usuário") },
                shape = RoundedCornerShape(20.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedLabelColor = Color(0xFFB39DDB),
                    unfocusedLabelColor = Color(0xFFB39DDB),
                    cursorColor = Color(0xFF9575CD)
                )
            )

            Spacer(modifier = Modifier.height(10.dp))

            OutlinedTextField(
                value = Email,
                onValueChange = { Email = it },
                label = { Text("Email") },
                shape = RoundedCornerShape(20.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedLabelColor = Color(0xFFB39DDB),
                    unfocusedLabelColor = Color(0xFFB39DDB),
                    cursorColor = Color(0xFF9575CD)
                )
            )

            Spacer(modifier = Modifier.height(10.dp))

            OutlinedTextField(
                value = Senha,
                onValueChange = { Senha = it },
                label = { Text("Senha") },
                shape = RoundedCornerShape(20.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedLabelColor = Color(0xFFB39DDB),
                    unfocusedLabelColor = Color(0xFFB39DDB),
                    cursorColor = Color(0xFF9575CD)
                )
            )

            Spacer(modifier = Modifier.height(10.dp))

            OutlinedTextField(
                value = ConfirmarSenha,
                onValueChange = { ConfirmarSenha = it },
                label = { Text("Confirmar Senha") },
                shape = RoundedCornerShape(20.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedLabelColor = Color(0xFFB39DDB),
                    unfocusedLabelColor = Color(0xFFB39DDB),
                    cursorColor = Color(0xFF9575CD)
                )
            )

            Spacer(modifier = Modifier.height(10.dp))

            // Mensagem de erro
            if (mensagemErro.isNotEmpty()) {
                Text(
                    text = mensagemErro,
                    color = Color.Red,
                    fontSize = 12.sp
                )
                Spacer(modifier = Modifier.height(8.dp))
            }

            Button(
                onClick = {
                    // Validações básicas
                    when {
                        Nome.isEmpty() || Senha.isEmpty() || Email.isEmpty() -> {
                            mensagemErro = "Preencha todos os campos"
                        }
                        Senha != ConfirmarSenha -> {
                            mensagemErro = "As senhas não coincidem"
                        }
                        else -> {
                            coroutineScope.launch {
                                try {
                                    val resposta = RetrofitClient.api.cadastrar(
                                        UsuarioRequest(nome = Nome, senha = Senha)
                                    )
                                    if (resposta.isSuccessful) {
                                        onCadastroSucesso()
                                    } else {
                                        mensagemErro = "Usuário já existe"
                                    }
                                } catch (e: Exception) {
                                    mensagemErro = "Erro de conexão"
                                }
                            }
                        }
                    }
                },
                modifier = Modifier
                    .width(290.dp)
                    .height(50.dp)
            ) {
                Text("Cadastrar")
            }

            Spacer(modifier = Modifier.height(15.dp))

            Text(
                text = "Já tem uma conta? Entrar",
                fontSize = 15.sp,
                color = Color(0xFF8D82AD),
                fontWeight = FontWeight.Normal,
                modifier = Modifier
                    .offset(x = 100.dp)
                    .clickable { onVoltarLogin() }
            )
        }
    }
}