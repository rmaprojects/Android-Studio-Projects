package com.rmaprojects.studentmonitoring.core.domain.use_case.submit

import com.rmaprojects.studentmonitoring.core.domain.repository.StudentMonitoringRepository

class SubmitViolationUseCase(
    private val repository: StudentMonitoringRepository
) {
    suspend operator fun invoke(
        studentName: String,
        studentClass: String,
        description: String,
        point: Int
    ) {
        return repository.inputViolationData(studentName, studentClass, description, point)
    }
}