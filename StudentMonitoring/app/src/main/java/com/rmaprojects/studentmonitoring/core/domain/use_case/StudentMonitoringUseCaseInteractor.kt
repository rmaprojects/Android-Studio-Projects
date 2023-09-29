package com.rmaprojects.studentmonitoring.core.domain.use_case

import com.rmaprojects.studentmonitoring.core.domain.repository.StudentMonitoringRepository
import com.rmaprojects.studentmonitoring.core.domain.use_case.auth.AuthUseCase
import com.rmaprojects.studentmonitoring.core.domain.use_case.history.ViolationHistoryUseCase
import com.rmaprojects.studentmonitoring.core.domain.use_case.submit.SubmitViolationUseCase
import javax.inject.Inject

class StudentMonitoringUseCaseInteractor @Inject constructor(
    private val repository: StudentMonitoringRepository
): StudentMonitoringUseCases {
    override val authUseCase: AuthUseCase
        get() = AuthUseCase(repository)
    override val submitViolationUseCase: SubmitViolationUseCase
        get() = SubmitViolationUseCase(repository)
    override val historyUseCase: ViolationHistoryUseCase
        get() = ViolationHistoryUseCase(repository)
}