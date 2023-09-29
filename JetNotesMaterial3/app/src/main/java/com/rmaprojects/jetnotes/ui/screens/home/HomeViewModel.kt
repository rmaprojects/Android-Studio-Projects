package com.rmaprojects.jetnotes.ui.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rmaprojects.jetnotes.data.local.database.model.Note
import com.rmaprojects.jetnotes.data.repository.NotesRepository
import com.rmaprojects.jetnotes.ui.screens.home.state.NoteState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: NotesRepository
): ViewModel() {

    private val _noteState: MutableStateFlow<NoteState<List<Note>>> = MutableStateFlow(
        NoteState.Empty
    )
    val noteState: StateFlow<NoteState<List<Note>>> = _noteState.asStateFlow()

    private fun getAllNote() {
        viewModelScope.launch {
            repository.getAllNotes().collect { noteList ->
                if (noteList.isNotEmpty())
                    _noteState.emit(NoteState.NotEmpty(noteList))
                else _noteState.emit(NoteState.Empty)
            }
        }
    }

    fun deleteNote(note: Note) {
        viewModelScope.launch { repository.deleteNote(note) }
    }

    init {
        getAllNote()
    }
}