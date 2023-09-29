package com.rmaprojects.jetnotes.ui.screens.searchnote

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rmaprojects.jetnotes.data.local.database.model.Note
import com.rmaprojects.jetnotes.data.repository.NotesRepository
import com.rmaprojects.jetnotes.ui.screens.searchnote.event.SearchNoteEvent
import com.rmaprojects.jetnotes.ui.screens.searchnote.event.SearchNoteUiEvent
import com.rmaprojects.jetnotes.ui.screens.searchnote.state.SearchFieldState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchNoteViewModel @Inject constructor(
    private val repository: NotesRepository
): ViewModel() {
    private val _searchQuery = mutableStateOf(SearchFieldState())
    val searchQuery = _searchQuery

    private val _searchListState: MutableState<List<Note>> = mutableStateOf(emptyList())
    val searchListState = _searchListState

    private val _searchNoteUiEventFlow = MutableSharedFlow<SearchNoteUiEvent>()
    val searchNoteUiEventFlow = _searchNoteUiEventFlow.asSharedFlow()

    fun onEvent(event: SearchNoteEvent) {
        when (event) {
            is SearchNoteEvent.EnteredQuery -> {
                _searchQuery.value = searchQuery.value.copy(
                    text = event.newQuery
                )
            }
            is SearchNoteEvent.QueryChangeFocus -> {
                _searchQuery.value = searchQuery.value.copy(
                    isHintVisible = !event.focusState.isFocused && searchQuery.value.text.isBlank()
                )
            }
            is SearchNoteEvent.DeleteNote -> {
                viewModelScope.launch { repository.deleteNote(event.targetNote) }
            }
            is SearchNoteEvent.FindNote -> {
                viewModelScope.launch {
                    try {
                        if (_searchQuery.value.text.isNotBlank() || _searchQuery.value.text.isNotEmpty()) {
                            repository.searchNote(
                                _searchQuery.value.text
                            ).catch { e ->
                                _searchNoteUiEventFlow.emit(SearchNoteUiEvent.Error(e.message ?: "Error Occurred"))
                            }.collectLatest {  noteList ->
                                if (noteList.isEmpty()) {
                                    _searchNoteUiEventFlow.emit(SearchNoteUiEvent.NotFoundNote)
                                    return@collectLatest
                                }
                                _searchNoteUiEventFlow.emit(SearchNoteUiEvent.FoundNote)
                                _searchListState.value = noteList
                            }
                        } else {
                            _searchNoteUiEventFlow.emit(SearchNoteUiEvent.EmptyQuery)
                        }
                    } catch (e: Exception) {
                        Log.d("ERROR_FETCH_NOTE", e.toString())
                        _searchNoteUiEventFlow.emit(SearchNoteUiEvent.Error(e.message ?: "Error Occurred"))
                    }
                }
            }
        }
    }
}