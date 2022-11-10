package com.rmaprojects.jadwalsholatapp.di

import com.rmaprojects.jadwalsholatapp.data.api.config.HadithApiConfig
import com.rmaprojects.jadwalsholatapp.data.api.config.PrayerApiConfig
import com.rmaprojects.jadwalsholatapp.data.repository.Repository

object Injection {
    fun provideRepository() : Repository {
        val prayerApi = PrayerApiConfig.getApiService()
        val hadithApi = HadithApiConfig.getApiService()
        return Repository(prayerApi, hadithApi)
    }
}