package com.rmaprojects.studentmonitoring.presentation.ui.pages.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rmaprojects.studentmonitoring.core.data.source.local.kotpref.SharedPreference
import com.rmaprojects.studentmonitoring.core.domain.use_case.StudentMonitoringUseCases
import com.rmaprojects.studentmonitoring.presentation.ui.pages.home.event.HomeEvent
import com.rmaprojects.studentmonitoring.presentation.ui.state.LogicState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    useCases: StudentMonitoringUseCases
) : ViewModel() {

    private val submitViolationUseCase = useCases.submitViolationUseCase
    private val authUseCase = useCases.authUseCase

    private val _homeState = MutableStateFlow<LogicState?>(null)
    val homeState = _homeState.asStateFlow()

    fun onEvent(event: HomeEvent) {
        when (event) {
            is HomeEvent.SubmitViolation -> {
                viewModelScope.launch {
                    try {
                        _homeState.emit(LogicState.Loading)
                        submitViolationUseCase(
                            event.studentName,
                            event.studentClass,
                            event.violationDescription,
                            event.points
                        )
                        _homeState.emit(LogicState.Success)
                    } catch (e: Exception) {
                        _homeState.emit(
                            LogicState.Error(
                                e.message ?: "Error when submitting violation"
                            )
                        )
                    }
                }
            }

            HomeEvent.DismissStates -> {
                viewModelScope.launch { _homeState.emit(null) }
            }

            HomeEvent.LogOut -> {
                viewModelScope.launch {
                    authUseCase.logOut()
                    SharedPreference.savedName = null
                    SharedPreference.savedUuid = null
                }
            }
        }
    }
}