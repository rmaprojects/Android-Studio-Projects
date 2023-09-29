package com.rmaprojects.jetnotes.ui.screens.insertnote.state

sealed class InsertNoteUiEvent {
    data class ShowSnackbar(val message: String) : InsertNoteUiEvent()
    object SaveNote : InsertNoteUiEvent()
}

sealed class DeleteNoteUiEvent {
    data class ShowSnackbar(val message: String): DeleteNoteUiEvent()
    object DeleteNote: DeleteNoteUiEvent()
}