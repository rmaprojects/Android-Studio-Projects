package com.rmaproject.notzeez.pages.addnote

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rmaproject.notzeez.model.Note
import com.rmaproject.notzeez.repository.MainRepository
import com.rmaproject.notzeez.util.Status
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class AddNoteViewModel(private val repository: MainRepository) : ViewModel() {

    private val _addNoteStatus = MutableLiveData<Status<Unit>>()
    val addNoteStatus: LiveData<Status<Unit>> = _addNoteStatus

    fun inputNotes(title: String, content: String) {

        _addNoteStatus.postValue(Status.Loading())

        viewModelScope.launch(Dispatchers.Main) {
            val result = repository.addNotes(title, content)
            _addNoteStatus.postValue(Status.Success(result))
        }
    }

    fun editNotes(noteId: String, title: String, content: String) {
        _addNoteStatus.postValue(Status.Loading())
        viewModelScope.launch {
            val result = repository.addNotes(title, content, noteId)
            _addNoteStatus.postValue(Status.Success(result))
        }
    }

    fun insertNoteToDatabase(note: Note) {
        viewModelScope.launch {
            repository.insertNote(note)
        }
    }

    fun editNoteFromDatabase(note: Note) {
        viewModelScope.launch {
            repository.editNote(note)
        }
    }

    fun getSingleNoteFromDatabase(noteId: Int): Flow<Note> {
        return repository.getSingleNoteInDatabase(noteId)
    }
}