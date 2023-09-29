package com.rmaproject.notzeez.pages.favourites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rmaproject.notzeez.model.Note
import com.rmaproject.notzeez.repository.MainRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class FavouriteViewModel(
    private val repository: MainRepository
): ViewModel() {
    fun getAllNotes(): Flow<List<Note>> {
        return repository.getAllNotesInDatabase()
    }

    fun deleteNote(note: Note) {
        viewModelScope.launch {
            repository.deleteNote(note)
        }
    }
}