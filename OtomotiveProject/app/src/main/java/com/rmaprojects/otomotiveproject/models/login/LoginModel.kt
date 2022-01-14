package com.rmaprojects.otomotiveproject.models.login


import com.google.gson.annotations.SerializedName

data class LoginModel(
    @SerializedName("code")
    val code: Int,
    @SerializedName("data_login")
    val dataLogin: List<DataLogin>,
    @SerializedName("is_admin")
    val isAdmin: Boolean,
    @SerializedName("message")
    val message: String,
    @SerializedName("status")
    val status: Boolean
)