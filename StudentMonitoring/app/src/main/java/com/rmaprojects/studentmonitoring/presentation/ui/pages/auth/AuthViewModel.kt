package com.rmaprojects.studentmonitoring.presentation.ui.pages.auth

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rmaprojects.studentmonitoring.core.domain.use_case.StudentMonitoringUseCases
import com.rmaprojects.studentmonitoring.presentation.ui.pages.auth.event.AuthEvent
import com.rmaprojects.studentmonitoring.presentation.ui.state.LogicState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    useCases: StudentMonitoringUseCases
) : ViewModel() {
    private val authUseCase = useCases.authUseCase

    private val _loginState = MutableStateFlow<LogicState?>(null)
    val loginState = _loginState

    private val _registerState = MutableStateFlow<LogicState?>(null)
    val registerState = _registerState.asStateFlow()

    fun onEvent(event: AuthEvent) {
        when (event) {
            is AuthEvent.BeginLogin -> {
                viewModelScope.launch {
                    _loginState.emit(LogicState.Loading)
                    try {
                        authUseCase.loginUser(event.email, event.password)
                        _loginState.emit(LogicState.Success)
                    } catch (e: Exception) {
                        _loginState.emit(
                            LogicState.Error(
                                e.message ?: "Error occurred when log in"
                            )
                        )
                    }
                }
            }
            is AuthEvent.RegisterUser -> {
                viewModelScope.launch {
                    _registerState.emit(LogicState.Loading)
                    try {
                        authUseCase.registerUser(
                            event.email,
                            event.password,
                            event.name
                        )
                        _registerState.emit(LogicState.Success)
                    } catch (e: Exception) {
                        _registerState.emit(
                            LogicState.Error(
                                e.message ?: "Error occurred when registering user"
                            )
                        )
                        Log.d("REG_ERR", e.toString())
                    }
                }
            }
            AuthEvent.DismissDialog -> {
                viewModelScope.launch {
                    _registerState.emit(null)
                    _loginState.emit(null)
                }
            }
        }
    }
}