package com.rmaprojects.jadwalsholatapp.data.api.model.prayertime


import com.google.gson.annotations.SerializedName

data class PrayerTimeModel(
    @SerializedName("city")
    val city: String?,
    @SerializedName("lat")
    val lat: Double?,
    @SerializedName("latitude")
    val latitude: String?,
    @SerializedName("lng")
    val lng: Double?,
    @SerializedName("longitude")
    val longitude: String?,
    @SerializedName("province")
    val province: String?,
    @SerializedName("times")
    val times: List<Time?>?
)