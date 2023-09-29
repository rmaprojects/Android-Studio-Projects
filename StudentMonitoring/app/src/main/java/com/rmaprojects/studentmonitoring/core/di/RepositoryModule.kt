package com.rmaprojects.studentmonitoring.core.di

import com.rmaprojects.studentmonitoring.core.data.repository.StudentMonitoringRepositoryImpl
import com.rmaprojects.studentmonitoring.core.domain.repository.StudentMonitoringRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module(includes = [NetworkModule::class])
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun provideRepository(repositoryImpl: StudentMonitoringRepositoryImpl): StudentMonitoringRepository
}