package com.rmaprojects.jetnotes.data.repository

import com.rmaprojects.jetnotes.data.local.database.model.Note
import com.rmaprojects.jetnotes.data.local.database.NotesDatabase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NotesRepository @Inject constructor(
    private val database: NotesDatabase
) {
    suspend fun insertNote(note: Note) {
        database.notesDao().insertNote(note)
    }

    suspend fun deleteNote(note: Note) {
        database.notesDao().deleteNote(note)
    }

    fun getAllNotes(): Flow<List<Note>> {
        return database.notesDao().getAllNotes()
    }

    suspend fun getNoteById(notesId: Int): Note? {
        return database.notesDao().getNoteById(notesId)
    }

    fun searchNote(searchQuery: String): Flow<List<Note>> {
        return database.notesDao().searchNote(searchQuery)
    }


}