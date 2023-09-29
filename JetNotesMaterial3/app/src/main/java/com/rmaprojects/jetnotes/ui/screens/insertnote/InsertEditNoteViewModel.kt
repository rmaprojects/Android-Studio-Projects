package com.rmaprojects.jetnotes.ui.screens.insertnote

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rmaprojects.jetnotes.data.local.database.model.Note
import com.rmaprojects.jetnotes.data.repository.NotesRepository
import com.rmaprojects.jetnotes.ui.screens.insertnote.event.InsertNoteEvent
import com.rmaprojects.jetnotes.ui.screens.insertnote.state.DeleteNoteUiEvent
import com.rmaprojects.jetnotes.ui.screens.insertnote.state.InsertNoteUiEvent
import com.rmaprojects.jetnotes.ui.screens.insertnote.state.TextFieldState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class InsertEditNoteViewModel @Inject constructor(
    private val repository: NotesRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _noteTitle = mutableStateOf(TextFieldState(hint = "Enter Title"))
    val noteTitle: State<TextFieldState> = _noteTitle

    private val _noteContent = mutableStateOf(TextFieldState(hint = "Enter Content"))
    val noteContent: State<TextFieldState> = _noteContent

    private val _insertNoteUiEvent = MutableSharedFlow<InsertNoteUiEvent>()
    val insertNoteUiEvent: SharedFlow<InsertNoteUiEvent> = _insertNoteUiEvent.asSharedFlow()

    private val _deleteNoteUiEvent = MutableSharedFlow<DeleteNoteUiEvent>()
    val deleteNoteUiEvent: SharedFlow<DeleteNoteUiEvent> = _deleteNoteUiEvent.asSharedFlow()

    val noteId: Int = savedStateHandle["noteId"] ?: -1
    private var _currentNoteId: Int? = null
    private var _noteTime: Long? = null

    fun onEvent(event: InsertNoteEvent) {
        when (event) {
            is InsertNoteEvent.ChangeContentFocus -> {
                _noteContent.value = noteContent.value.copy(
                    isHintVisible = !event.focusState.isFocused && noteContent.value.text.isBlank()
                )
            }

            is InsertNoteEvent.ChangeTitleFocus -> {
                _noteTitle.value = noteTitle.value.copy(
                    isHintVisible = !event.focusState.isFocused && noteTitle.value.text.isBlank()
                )
            }

            is InsertNoteEvent.EnteredContent -> {
                _noteContent.value = noteContent.value.copy(
                    text = event.value
                )
            }

            is InsertNoteEvent.EnteredTitle -> {
                _noteTitle.value = noteTitle.value.copy(
                    text = event.value
                )
            }

            is InsertNoteEvent.SaveNote -> {
                viewModelScope.launch {
                    try {
                        repository.insertNote(
                            Note(
                                id = _currentNoteId,
                                title = _noteTitle.value.text,
                                content = _noteContent.value.text,
                                time = System.currentTimeMillis(),
                            )
                        )
                        _insertNoteUiEvent.emit(InsertNoteUiEvent.SaveNote)
                    } catch (e: Exception) {
                        _insertNoteUiEvent.emit(
                            InsertNoteUiEvent.ShowSnackbar(
                                e.message ?: "Error occurred, cannot save note"
                            )
                        )
                        Log.d("ERR_ADD_NOTE", e.toString())
                    }
                }
            }

            is InsertNoteEvent.DeleteNote -> {
                viewModelScope.launch {
                    try {
                        repository.deleteNote(
                            Note(
                                id = noteId,
                                title = _noteTitle.value.text,
                                content = _noteContent.value.text,
                                time = _noteTime!!
                            )
                        )
                        _deleteNoteUiEvent.emit(DeleteNoteUiEvent.DeleteNote)
                    } catch (e: Exception) {
                        _deleteNoteUiEvent.emit(
                            DeleteNoteUiEvent.ShowSnackbar(
                                e.message ?: "Error when deleting notes"
                            )
                        )
                    }
                }
            }
        }
    }

    init {
        if (noteId != -1) viewModelScope.launch {
            repository.getNoteById(noteId)?.also { note ->
                _currentNoteId = note.id
                _noteTitle.value = noteTitle.value.copy(
                    text = note.title ?: "",
                    isHintVisible = false
                )
                _noteContent.value = noteContent.value.copy(
                    text = note.content ?: "",
                    isHintVisible = false
                )
                _noteTime = note.time
            }
        }
    }
}