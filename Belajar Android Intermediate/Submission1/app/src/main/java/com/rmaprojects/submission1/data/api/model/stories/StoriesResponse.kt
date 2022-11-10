package com.rmaprojects.submission1.data.api.model.stories


import com.google.gson.annotations.SerializedName

data class StoriesResponse(
    @SerializedName("error")
    val error: Boolean?,
    @SerializedName("listStory")
    val listStory: List<Story?>?,
    @SerializedName("message")
    val message: String?
)