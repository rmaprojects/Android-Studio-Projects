package com.rmaprojects.studentmonitoring.core.domain.repository

import com.rmaprojects.studentmonitoring.core.domain.model.ViolationHistoryData

interface StudentMonitoringRepository {
    suspend fun register(
        newEmail: String,
        newPassword: String,
        name: String
    )

    suspend fun login(
        loginEmail: String,
        loginPassword: String
    )

    suspend fun logOut()

    suspend fun inputViolationData(
        studentName: String,
        studentClass: String,
        description: String,
        point: Int
    )

    suspend fun getAllViolationData(): List<ViolationHistoryData>
}