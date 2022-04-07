package com.rmaproject.sholattrackeranak.api.`interface`

import com.google.gson.GsonBuilder
import com.rmaproject.sholattrackeranak.api.model.JadwalSholatModel
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface JadwalSholatInterface {

    @GET("today.json")
    suspend fun getJadwalSholat(
        @Query("city") kota:String
    ): Response<JadwalSholatModel>

    companion object {
        private const val BASE_URL = "https://api.pray.zone/v2/times/"
        fun createApi(): JadwalSholatInterface {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
                .build()
                .create(JadwalSholatInterface::class.java)
        }
    }
}