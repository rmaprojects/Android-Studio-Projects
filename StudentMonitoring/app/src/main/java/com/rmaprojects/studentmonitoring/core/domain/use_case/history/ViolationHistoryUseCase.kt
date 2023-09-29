package com.rmaprojects.studentmonitoring.core.domain.use_case.history

import com.rmaprojects.studentmonitoring.core.domain.model.ViolationHistoryData
import com.rmaprojects.studentmonitoring.core.domain.repository.StudentMonitoringRepository

class ViolationHistoryUseCase(
    private val repository: StudentMonitoringRepository
) {

    suspend fun getAllViolationHistory(): List<ViolationHistoryData> {
        return repository.getAllViolationData()
    }
}