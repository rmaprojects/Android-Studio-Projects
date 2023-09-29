package com.rmaprojects.studentmonitoring.core.di

import com.rmaprojects.studentmonitoring.core.domain.use_case.StudentMonitoringUseCaseInteractor
import com.rmaprojects.studentmonitoring.core.domain.use_case.StudentMonitoringUseCases
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {
    @Binds
    abstract fun provideUseCase(interactor: StudentMonitoringUseCaseInteractor): StudentMonitoringUseCases
}