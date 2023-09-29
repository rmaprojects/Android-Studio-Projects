package com.rmaprojects.jetnotes.ui.screens.searchnote.event

import androidx.compose.ui.focus.FocusState
import com.rmaprojects.jetnotes.data.local.database.model.Note

sealed class SearchNoteEvent {
    data class EnteredQuery(val newQuery: String): SearchNoteEvent()
    data class QueryChangeFocus(val focusState: FocusState): SearchNoteEvent()
    data class DeleteNote(val targetNote: Note): SearchNoteEvent()
    object FindNote: SearchNoteEvent()
}
