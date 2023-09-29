package com.rmaprojects.jetnotes.data.local.database

import androidx.room.*
import com.rmaprojects.jetnotes.data.local.database.model.Note
import kotlinx.coroutines.flow.Flow

@Dao
interface NotesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote(note: Note)

    @Delete
    suspend fun deleteNote(note: Note)

    @Query("SELECT * FROM note ORDER BY time ASC")
    fun getAllNotes(): Flow<List<Note>>

    @Query("SELECT * FROM note WHERE id = :notesId")
    suspend fun getNoteById(notesId: Int) : Note?

    @Query("SELECT * FROM note WHERE title LIKE '%' ||:searchQuery|| '%' OR content LIKE '%' ||:searchQuery|| '%'")
    fun searchNote(searchQuery: String) : Flow<List<Note>>
}