package com.rmaprojects.otomotiveproject.models.login


import com.google.gson.annotations.SerializedName

data class DataLogin(
    @SerializedName("alamat")
    val alamat: String,
    @SerializedName("is_approved")
    val isApproved: String,
    @SerializedName("last_km")
    val lastKm: String,
    @SerializedName("last_service")
    val lastService: String,
    @SerializedName("nama_customer")
    val namaCustomer: String,
    @SerializedName("no_anggota")
    val noAnggota: String,
    @SerializedName("no_plat")
    val noPlat: String,
    @SerializedName("no_rangka")
    val noRangka: String
)