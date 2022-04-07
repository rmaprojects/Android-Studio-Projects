package com.rmaproject.sholattrackeranak.api.model


import com.google.gson.annotations.SerializedName

data class JadwalSholatModel(
    @SerializedName("code")
    val code: Int,
    @SerializedName("results")
    val results: Results,
    @SerializedName("status")
    val status: String
)