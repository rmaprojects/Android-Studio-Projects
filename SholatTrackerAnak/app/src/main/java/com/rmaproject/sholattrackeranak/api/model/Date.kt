package com.rmaproject.sholattrackeranak.api.model


import com.google.gson.annotations.SerializedName

data class Date(
    @SerializedName("gregorian")
    val gregorian: String,
    @SerializedName("hijri")
    val hijri: String,
    @SerializedName("timestamp")
    val timestamp: Int
)