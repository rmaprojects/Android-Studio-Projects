package com.rmaprojects.studentmonitoring.presentation.ui.pages.history.event

sealed class HistoryEvent {
    data object FetchHistoryFromServer: HistoryEvent()
}