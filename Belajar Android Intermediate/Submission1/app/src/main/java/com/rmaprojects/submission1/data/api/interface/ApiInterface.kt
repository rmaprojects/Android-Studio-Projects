package com.rmaprojects.submission1.data.api.`interface`

import com.rmaprojects.submission1.data.api.model.login.LoginResponse
import com.rmaprojects.submission1.data.api.model.register.RegisterResponse
import com.rmaprojects.submission1.data.api.model.stories.StoriesResponse
import com.rmaprojects.submission1.data.api.model.upload.UploadResponse
import okhttp3.RequestBody
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Path

interface ApiInterface {

    @FormUrlEncoded
    @POST("register")
    suspend fun registerUser(
        @Field("name") name: String,
        @Field("email") email: String,
        @Field("password") password: String
    ): RegisterResponse

    @FormUrlEncoded
    @POST("login")
    suspend fun loginUser(
        @Field("email") email: String,
        @Field("password") password: String
    ): LoginResponse

    @GET("stories")
    suspend fun getStories(
        @Header("Authorization") value: String
    ): StoriesResponse

    @GET("stories/{id}")
    suspend fun getDetailStory(
        @Header("Authorization") value: String,
        @Path("id") id : String
    )

    @Multipart
    @POST("stories")
    suspend fun uploadStories(
        @Header("Authorization") value: String,
        @Part("description") description: RequestBody,
        @Part("photo") photo: RequestBody
    ) : UploadResponse

    companion object {
        private const val BASE_URL = "https://story-api.dicoding.dev/v1/"

        fun createApi(): ApiInterface {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(ApiInterface::class.java)
        }
    }
}