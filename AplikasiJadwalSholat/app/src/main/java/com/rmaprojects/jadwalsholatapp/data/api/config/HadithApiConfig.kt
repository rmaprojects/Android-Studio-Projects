package com.rmaprojects.jadwalsholatapp.data.api.config

import androidx.viewbinding.BuildConfig
import com.rmaprojects.jadwalsholatapp.data.api.`interface`.HadithApiInterface
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class HadithApiConfig {
    companion object {
        private const val BASE_URL = "https://hadis-api-id.vercel.app/hadith/"

        fun getApiService() : HadithApiInterface {

            val loggingInterceptor = if (BuildConfig.DEBUG) {
                HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
            } else {
                HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.NONE)
            }

            val client = OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .build()

            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(HadithApiInterface::class.java)
        }
    }
}