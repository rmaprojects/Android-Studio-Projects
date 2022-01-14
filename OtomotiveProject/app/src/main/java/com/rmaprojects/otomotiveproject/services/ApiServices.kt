package com.rmaprojects.otomotiveproject.services

import com.google.gson.Gson
import com.rmaprojects.otomotiveproject.models.login.LoginModel
import com.rmaprojects.otomotiveproject.models.register.RegisterModel
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiServices {

    @POST("user_login.php")
    suspend fun userLogin(
        @Query("no_anggota") nomor_anggota:String,
        @Query("password") password:String
    ) : LoginModel

    @POST("user_register.php")
    suspend fun userrRgister(
        @Query("no_anggota") nomor_anggota:String,
        @Query("password") password:String,
        @Query("no_plat") nomor_plat:String,
        @Query("nama_customer") nama_customer:String,
        @Query("alamat") alamat:String
    ) : RegisterModel

    companion object {
        private const val BASE_URL = "http://192.168.3.10/"
        fun create():ApiServices {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(Gson()))
                .build()
                .create(ApiServices::class.java)
        }
    }

}