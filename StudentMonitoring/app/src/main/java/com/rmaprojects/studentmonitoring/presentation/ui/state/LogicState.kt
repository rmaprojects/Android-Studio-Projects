package com.rmaprojects.studentmonitoring.presentation.ui.state

sealed class LogicState {
    data object Loading: LogicState()
    data object Success: LogicState()
    data class Error(val message: String): LogicState()
}
