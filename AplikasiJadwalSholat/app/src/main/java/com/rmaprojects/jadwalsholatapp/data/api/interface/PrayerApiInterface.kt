package com.rmaprojects.jadwalsholatapp.data.api.`interface`

import com.rmaprojects.jadwalsholatapp.data.api.model.prayertime.PrayerTimeModel
import retrofit2.http.GET
import retrofit2.http.Query

interface PrayerApiInterface {
    @GET("day")
    suspend fun getPrayerTime(
        @Query("latitude") latitude:Double,
        @Query("longitude") longitude:Double
    ) : PrayerTimeModel
}