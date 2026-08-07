package com.example.orbitrockettcc.Screens.Logar


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
import com.example.orbitrockettcc.API.RetrofitClient
import com.example.orbitrockettcc.API.UsuarioRequest
import com.example.orbitrockettcc.R
import com.example.orbitrockettcc.Screens.fundoEstrela
import kotlinx.coroutines.launch






@Composable
fun LoginScreen(
    onFinish: () -> Unit,
    onCadastro: () -> Unit
) {


    Box(
        modifier = Modifier.fillMaxSize()
    ) {


        fundoEstrela()


        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {




            Image(
                painter = painterResource(R.drawable.fogo),
                contentDescription = null,
                modifier = Modifier
                    .size(75.dp)
                    .offset(
                        x = (-50).dp,
                        y = 135.dp
                    )
                    .rotate(-140f)
            )


            Image(
                painter = painterResource(R.drawable.foguete),
                contentDescription = null,
                modifier = Modifier
                    .size(220.dp)
                    .offset(
                        x = (3).dp,
                        y = -100.dp


                    )




            )


            Image(
                painter = painterResource(R.drawable.brilho),
                contentDescription = null,
                modifier = Modifier
                    .size(280.dp)
                    .offset(
                        x = -2.dp,
                        y = -325.dp
                    )
                    .alpha(0.7f)
            )
        }


        Spacer(modifier = Modifier.height(20.dp))


        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(40.dp)
                .offset(
                    x = 20.dp,
                    y = 350.dp
                )


        ) {




            var Nome by remember {
                mutableStateOf("")
            }


            OutlinedTextField(
                value = Nome,
                onValueChange = {
                    Nome = it
                },
                label = {
                    Text("Usuário")
                },




                shape = RoundedCornerShape(20.dp),
                colors = OutlinedTextFieldDefaults.colors(


                    focusedLabelColor = Color(0xFFB39DDB),
                    unfocusedLabelColor = Color(0xFFB39DDB),
                    cursorColor = Color(0xFF9575CD)
                )
            )




            Spacer(modifier = Modifier.height(10.dp))


            var Senha by remember {
                mutableStateOf("")
            }




            OutlinedTextField(
                value = Senha,
                onValueChange = {
                    Senha = it
                },
                label = {
                    Text("Senha")
                },




                shape = RoundedCornerShape(20.dp),
                colors = OutlinedTextFieldDefaults.colors(


                    focusedLabelColor = Color(0xFFB39DDB),
                    unfocusedLabelColor = Color(0xFFB39DDB),
                    cursorColor = Color(0xFF9575CD)
                )
            )






            Spacer(modifier = Modifier.height(15.dp))




            Text(
                text = "Esqueceu sua senha?",
                fontSize = 13.sp,
                color = Color(0xFF4D3E79),
                fontWeight = FontWeight.Normal,
                modifier = Modifier.offset(
                    x = 80.dp
                )


            )






            Spacer(modifier = Modifier.height(20.dp))


            val coroutineScope = rememberCoroutineScope() // ← antes do Column


            Button(
                onClick = {
                    coroutineScope.launch {
                        try {
                            val resposta = RetrofitClient.api.login(
                                UsuarioRequest(
                                    nome = Nome,
                                    email = "",
                                    senha = Senha
                                )
                            )
                            if (resposta.isSuccessful) {
                                onFinish()
                            }
                        } catch (e: Exception) {
                            // erro de conexão
                        }
                    }
                },
                modifier = Modifier
                    .width(290.dp)
                    .height(50.dp)
            ) {
                Text("Entrar")
            }




            Spacer(modifier = Modifier.height(15.dp))




            Text(
                text = "ou continue com",
                fontSize = 15.sp,
                color = Color(0xFF8D82AD),
                fontWeight = FontWeight.Normal,
                modifier = Modifier.offset(
                    x = 100.dp
                )




            )




            Spacer(modifier = Modifier.height(15.dp))




            Button(


                onClick = {},
                modifier = Modifier
                    .width(290.dp)
                    .height(50.dp)
            ) {
                Text("\uD83C\uDD56  Entrar com o google")


            }
            Spacer(modifier = Modifier.height(15.dp))


            Text(
                color = Color(0xFF8D82AD),
                text = "Ainda não tem uma conta? Cadastra-se",
                modifier = Modifier
                    .offset(x = 100.dp)
                    .clickable {
                        onCadastro()
                    }
            )
        }


    }










}

