package com.rmaprojects.submission1.data.api.model.upload


import com.google.gson.annotations.SerializedName

data class UploadResponse(
    @SerializedName("error")
    val error: Boolean,
    @SerializedName("message")
    val message: String
)