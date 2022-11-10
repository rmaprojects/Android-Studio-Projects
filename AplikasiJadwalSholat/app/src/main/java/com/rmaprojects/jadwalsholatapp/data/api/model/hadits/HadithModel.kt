package com.rmaprojects.jadwalsholatapp.data.api.model.hadits


import com.google.gson.annotations.SerializedName

data class HadithModel(
    @SerializedName("arab")
    val arab: String?,
    @SerializedName("id")
    val id: String?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("number")
    val number: Int?,
    @SerializedName("slug")
    val slug: String?
)