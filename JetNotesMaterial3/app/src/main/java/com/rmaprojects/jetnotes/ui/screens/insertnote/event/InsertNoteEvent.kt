package com.rmaprojects.jetnotes.ui.screens.insertnote.event

import androidx.compose.ui.focus.FocusState

sealed class InsertNoteEvent {
    data class EnteredTitle(val value: String): InsertNoteEvent()
    data class ChangeTitleFocus(val focusState: FocusState): InsertNoteEvent()
    data class EnteredContent(val value: String): InsertNoteEvent()
    data class ChangeContentFocus(val focusState: FocusState): InsertNoteEvent()
    object DeleteNote: InsertNoteEvent()
    object SaveNote: InsertNoteEvent()
}