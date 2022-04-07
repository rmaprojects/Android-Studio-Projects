package com.rmaproject.sholattrackeranak.api.model


import com.google.gson.annotations.SerializedName

data class GetNamaAnakModelItem(
    @SerializedName("id")
    val id: Int,
    @SerializedName("nama_anak")
    val namaAnak: String
)