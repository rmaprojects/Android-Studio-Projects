package com.rmaprojects.otomotiveproject.models.register


import com.google.gson.annotations.SerializedName

data class RegisterModel(
    @SerializedName("code")
    val code: Int,
    @SerializedName("data_anggota")
    val dataAnggota: List<DataAnggota>,
    @SerializedName("message")
    val message: String,
    @SerializedName("status")
    val status: Boolean
)