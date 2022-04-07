package com.rmaproject.sholattrackeranak.api.`interface`

import com.google.gson.GsonBuilder
import com.rmaproject.sholattrackeranak.api.model.GetNamaAnakModel
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface ApiInterface {

    @Headers("apikey:eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6InJjenFmbnhyaGdzYWV4b2Jkc2psIiwicm9sZSI6ImFub24iLCJpYXQiOjE2NDg5NDg5MDMsImV4cCI6MTk2NDUyNDkwM30.Cvjf5mtZg2mMTCCg6SF7ztBqWCPwaeIkMcwx7nA5Da0")
    @GET("daftar_anak")
    suspend fun getDaftarAnak(
        @Query("select") select:String
    ) : Response<GetNamaAnakModel>

    companion object {
        private const val BASE_URL = "https://rczqfnxrhgsaexobdsjl.supabase.co/rest/v1/"
        fun createApi(): ApiInterface {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
                .build()
                .create(ApiInterface::class.java)
        }
    }
}