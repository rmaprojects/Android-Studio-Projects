package com.rmaprojects.jadwalsholatapp.data.repository

import com.rmaprojects.jadwalsholatapp.data.api.`interface`.HadithApiInterface
import com.rmaprojects.jadwalsholatapp.data.api.`interface`.PrayerApiInterface
import com.rmaprojects.jadwalsholatapp.data.api.model.hadits.HadithModel
import com.rmaprojects.jadwalsholatapp.data.api.model.prayertime.PrayerTimeModel

class Repository(
    private val prayerTimeApi: PrayerApiInterface,
    private val hadithApi: HadithApiInterface
) {

    suspend fun getPrayerTime(
        longitude: Double,
        latitude: Double
    ): PrayerTimeModel {
        return prayerTimeApi.getPrayerTime(longitude, latitude)
    }

    suspend fun getHadith(
        slug: String,
        hadithNumber: Int
    ): HadithModel {
        return hadithApi.getHadith(slug, hadithNumber)
    }
}