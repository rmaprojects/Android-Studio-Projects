package com.rmaprojects.submission1.data.repository

import com.rmaprojects.submission1.data.api.`interface`.ApiInterface
import com.rmaprojects.submission1.data.api.model.login.LoginResponse
import com.rmaprojects.submission1.data.api.model.register.RegisterResponse
import com.rmaprojects.submission1.data.api.model.stories.StoriesResponse
import retrofit2.http.GET
import retrofit2.http.Header

class StoriesRepository(
    private val api: ApiInterface
) {

    suspend fun registerUser(
        name: String,
        email: String,
        password: String
    ): RegisterResponse = api.registerUser(name, email, password)

    suspend fun loginUser(
        email: String,
        password: String
    ) : LoginResponse = api.loginUser(email, password)

    suspend fun getStories(
        value: String
    ): StoriesResponse = api.getStories(value)
}