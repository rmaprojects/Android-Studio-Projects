package com.rmaprojects.storytest.ui.register.state

sealed class RegisterState {
    object Loading: RegisterState()
    data class Error(val message: String): RegisterState()
    object Success: RegisterState()
}
