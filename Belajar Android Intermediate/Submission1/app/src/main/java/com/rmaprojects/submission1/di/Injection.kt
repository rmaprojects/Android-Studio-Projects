package com.rmaprojects.submission1.di

import com.rmaprojects.submission1.data.api.`interface`.ApiInterface
import com.rmaprojects.submission1.data.repository.StoriesRepository

object Injection {
    fun provideRepository() : StoriesRepository {
        val api = ApiInterface.createApi()
        return StoriesRepository(api)
    }
}