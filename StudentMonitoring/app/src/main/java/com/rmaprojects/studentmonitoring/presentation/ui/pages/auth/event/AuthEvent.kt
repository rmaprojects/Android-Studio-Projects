package com.rmaprojects.studentmonitoring.presentation.ui.pages.auth.event

sealed class AuthEvent {
    data class BeginLogin(
        val email: String,
        val password: String
    ): AuthEvent()

    data class RegisterUser(
        val name: String,
        val email: String,
        val password: String
    ): AuthEvent()

    data object DismissDialog: AuthEvent()
}
