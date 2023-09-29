package com.rmaprojects.studentmonitoring.presentation.ui.pages.home.event

sealed class HomeEvent {
    data class SubmitViolation(
        val studentName: String,
        val studentClass: String,
        val violationDescription: String,
        val points: Int
    ): HomeEvent()

    data object DismissStates: HomeEvent()

    data object LogOut: HomeEvent()
}