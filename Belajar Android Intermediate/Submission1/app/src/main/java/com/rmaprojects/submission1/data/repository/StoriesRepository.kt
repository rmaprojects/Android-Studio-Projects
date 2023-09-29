package com.rmaprojects.submission1.data.repository

import com.rmaprojects.submission1.data.api.`interface`.ApiInterface
import com.rmaprojects.submission1.data.api.model.detail.StoryDetailResponse
import com.rmaprojects.submission1.data.api.model.login.LoginResponse
import com.rmaprojects.submission1.data.api.model.register.RegisterResponse
import com.rmaprojects.submission1.data.api.model.stories.StoriesResponse
import com.rmaprojects.submission1.data.api.model.upload.UploadResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody

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
    ): LoginResponse = api.loginUser(email, password)

    suspend fun getStories(): StoriesResponse = api.getStories()

    suspend fun getDetailStory(
        id: String
    ): StoryDetailResponse = api.getDetailStory(id)

    suspend fun uploadStories(
        description: RequestBody,
        photo: MultipartBody.Part?,
    ): UploadResponse = api.uploadStories(description, photo)
}