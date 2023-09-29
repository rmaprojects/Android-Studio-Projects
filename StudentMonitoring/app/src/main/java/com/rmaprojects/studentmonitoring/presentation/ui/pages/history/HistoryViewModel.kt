package com.rmaprojects.studentmonitoring.presentation.ui.pages.history

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rmaprojects.studentmonitoring.core.domain.use_case.StudentMonitoringUseCases
import com.rmaprojects.studentmonitoring.presentation.ui.pages.history.event.HistoryEvent
import com.rmaprojects.studentmonitoring.presentation.ui.pages.history.state.HistoryScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HistoryViewModel @Inject constructor(
    useCases: StudentMonitoringUseCases
) : ViewModel() {

    private val historyUseCases = useCases.historyUseCase

    private val _historyState = MutableStateFlow<HistoryScreenState>(HistoryScreenState.Loading)
    val historyState = _historyState.asStateFlow().stateIn(
        scope = viewModelScope,
        started = SharingStarted.Lazily,
        initialValue = HistoryScreenState.Loading
    )

    fun onEvent(event: HistoryEvent) {
        when (event) {
            HistoryEvent.FetchHistoryFromServer -> {
                viewModelScope.launch {
                    _historyState.emit(HistoryScreenState.Loading)
                    try {
                        val historyList = historyUseCases.getAllViolationHistory()
                        _historyState.emit(HistoryScreenState.Success(historyList))
                    } catch (e: Exception) {
                        _historyState.emit(
                            HistoryScreenState.Error(
                                e.message ?: "Error when fetching history"
                            )
                        )
                    }
                }
            }
        }
    }

    init {
        onEvent(HistoryEvent.FetchHistoryFromServer)
    }
}