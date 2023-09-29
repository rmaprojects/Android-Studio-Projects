package com.rmaprojects.storytest.di

import com.rmaprojects.storytest.data.source.remote.service.ApiInterface
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Injection {
    private const val BASE_URL = "https://story-api.dicoding.dev/v1"
    fun provideApiService(): ApiInterface {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiInterface::class.java)
    }
}