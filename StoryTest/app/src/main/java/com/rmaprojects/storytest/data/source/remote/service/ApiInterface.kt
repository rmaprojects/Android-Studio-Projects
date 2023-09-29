package com.rmaprojects.storytest.data.source.remote.service

import com.rmaprojects.storytest.data.source.remote.model.login.LoginUserResponse
import com.rmaprojects.storytest.data.source.remote.model.register.RegisterUserResponse
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Field
import retrofit2.http.POST

interface ApiInterface {

    @POST("register")
    suspend fun registerUser(
        @Field("name") name: String,
        @Field("email") email: String,
        @Field("password") password: String
    ): RegisterUserResponse

    @POST("login")
    suspend fun loginUser(
        @Field("email") email: String,
        @Field("password") password: String
    ): LoginUserResponse
}