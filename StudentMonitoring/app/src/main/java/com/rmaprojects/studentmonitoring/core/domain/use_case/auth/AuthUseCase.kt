package com.rmaprojects.studentmonitoring.core.domain.use_case.auth

import com.rmaprojects.studentmonitoring.core.domain.repository.StudentMonitoringRepository

class AuthUseCase(
    private val repository: StudentMonitoringRepository
) {
    suspend fun registerUser(
        email: String,
        password: String,
        name: String
    ) {
        return repository.register(email, password, name)
    }

    suspend fun loginUser(
        email: String,
        password: String
    ) {
        return repository.login(
            email,
            password
        )
    }

    suspend fun logOut() {
        return repository.logOut()
    }
}