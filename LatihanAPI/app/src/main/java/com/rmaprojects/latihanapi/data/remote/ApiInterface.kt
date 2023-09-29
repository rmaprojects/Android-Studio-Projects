package com.rmaprojects.latihanapi.data.remote

import com.rmaprojects.latihanapi.data.model.SearchMovieResponse
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {

    @GET("/")
    suspend fun searchMovie(
        @Query("s") search: String,
        @Query("apikey") apiKey: String = "d514b7db"
    ): SearchMovieResponse

    companion object {

        private const val BASE_URL = "https://omdbapi.com"

        fun createApi(): ApiInterface {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ApiInterface::class.java)
        }
    }
}