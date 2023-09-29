package com.rmaprojects.studentmonitoring.core.domain.use_case

import com.rmaprojects.studentmonitoring.core.domain.use_case.auth.AuthUseCase
import com.rmaprojects.studentmonitoring.core.domain.use_case.history.ViolationHistoryUseCase
import com.rmaprojects.studentmonitoring.core.domain.use_case.submit.SubmitViolationUseCase

interface StudentMonitoringUseCases {
    val authUseCase: AuthUseCase
    val submitViolationUseCase: SubmitViolationUseCase
    val historyUseCase: ViolationHistoryUseCase
}