package com.rmaprojects.storytest.data.source.remote.model.register


import com.google.gson.annotations.SerializedName

data class RegisterUserResponse(
    @SerializedName("error")
    val error: Boolean,
    @SerializedName("message")
    val message: String
)