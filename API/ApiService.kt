package com.example.tcc.API

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {

    @POST("auth/login")
    suspend fun login(@Body usuario: UsuarioRequest): Response<String>

    @POST("auth/cadastro")
    suspend fun cadastrar(@Body usuario: UsuarioRequest): Response<String>
}