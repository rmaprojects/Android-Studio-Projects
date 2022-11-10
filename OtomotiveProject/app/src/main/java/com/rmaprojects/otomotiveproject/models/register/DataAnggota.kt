package com.rmaprojects.otomotiveproject.models.register


import com.google.gson.annotations.SerializedName

data class DataAnggota(
    @SerializedName("alamat")
    val alamat: String,
    @SerializedName("nama_customer")
    val namaCustomer: String,
    @SerializedName("no_anggota")
    val noAnggota: String,
    @SerializedName("no_plat")
    val noPlat: String,
    @SerializedName("no_rangka")
    val noRangka: String
)