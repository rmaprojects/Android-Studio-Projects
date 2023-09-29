package com.rmaprojects.jetnotes.ui.screens.home.state

sealed class NoteState<out T: Any?> {
    data class NotEmpty<out T: Any>(val data: T): NoteState<T>()
    object Empty: NoteState<Nothing>()
}