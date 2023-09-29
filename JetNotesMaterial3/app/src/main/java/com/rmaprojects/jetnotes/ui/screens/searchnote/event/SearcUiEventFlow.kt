package com.rmaprojects.jetnotes.ui.screens.searchnote.event

sealed class SearchNoteUiEvent {
    object FoundNote: SearchNoteUiEvent()
    object NotFoundNote: SearchNoteUiEvent()
    object EmptyQuery: SearchNoteUiEvent()
    data class Error(val message: String): SearchNoteUiEvent()
}