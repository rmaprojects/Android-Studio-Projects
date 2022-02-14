package com.rmaprojects.signupsigninsupabase.services

import com.rmaprojects.signupsigninsupabase.model.postmodel.signin.SignInPost
import com.rmaprojects.signupsigninsupabase.model.postmodel.signup.SignUpPost
import com.rmaprojects.signupsigninsupabase.model.returnmodel.signin.SignInReturn
import com.rmaprojects.signupsigninsupabase.model.returnmodel.signup.SignUpReturn
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiServices {

    @Headers("apikey:eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJvbGUiOiJzZXJ2aWNlX3JvbGUiLCJpYXQiOjE2NDI3MjUwNTQsImV4cCI6MTk1ODMwMTA1NH0.Iwg-CrfqySKG0qgqtLA7i76Uxk8qRDJuI0oN6Qim6Ok")
    @POST("signup")
    suspend fun signUp(
        @Body signUpForm:SignUpPost
    ) : SignUpReturn

    @Headers("apikey:eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJvbGUiOiJzZXJ2aWNlX3JvbGUiLCJpYXQiOjE2NDI3MjUwNTQsImV4cCI6MTk1ODMwMTA1NH0.Iwg-CrfqySKG0qgqtLA7i76Uxk8qRDJuI0oN6Qim6Ok")
    @POST("token")
    suspend fun signIn(
        @Query("grant_type") grant:String,
        @Body signInForm:SignInPost
    ) : SignInReturn

    companion object {
        private const val BASE_URL = "https://lxghnrjozjmfxejvjrqy.supabase.co/auth/v1/"
        fun create() : ApiServices {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ApiServices::class.java)
        }
    }
}