package com.rmaprojects.studentmonitoring.presentation.ui.pages.history.state

import com.rmaprojects.studentmonitoring.core.domain.model.ViolationHistoryData

sealed class HistoryScreenState {
    data object Loading: HistoryScreenState()
    data class Error(val message: String): HistoryScreenState()
    data class Success(val historyList: List<ViolationHistoryData>): HistoryScreenState()
}
