package com.rmaprojects.studentmonitoring.core.data.repository

import com.rmaprojects.studentmonitoring.core.data.source.remote.RemoteDataSource
import com.rmaprojects.studentmonitoring.core.domain.model.ViolationHistoryData
import com.rmaprojects.studentmonitoring.core.domain.repository.StudentMonitoringRepository
import javax.inject.Inject

class StudentMonitoringRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource
) : StudentMonitoringRepository {
    override suspend fun register(newEmail: String, newPassword: String, name: String) {
        return remoteDataSource.register(newEmail, newPassword, name)
    }

    override suspend fun login(loginEmail: String, loginPassword: String) {
        return remoteDataSource.login(loginEmail, loginPassword)
    }

    override suspend fun logOut() {
        return remoteDataSource.logOut()
    }

    override suspend fun inputViolationData(
        studentName: String,
        studentClass: String,
        description: String,
        point: Int
    ) {
        return remoteDataSource.inputViolationData(
            studentName,
            studentClass,
            description,
            point
        )
    }

    override suspend fun getAllViolationData(): List<ViolationHistoryData> {
        return remoteDataSource.getAllViolationHistory().map {
            ViolationHistoryData(
                studentName = it.studentName,
                teacherName = it.teacherName ?: "Tidak ditemukan",
                description = it.description,
                point = it.point
            )
        }
    }


}