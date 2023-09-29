package com.rmaprojects.storytest.data.source.remote.model.login


import com.google.gson.annotations.SerializedName

data class LoginUserResponse(
    @SerializedName("error")
    val error: Boolean,
    @SerializedName("loginResult")
    val loginResult: LoginResult,
    @SerializedName("message")
    val message: String
)