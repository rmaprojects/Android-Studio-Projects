package com.rmaprojects.jetnotes.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.rmaprojects.jetnotes.data.local.database.model.Note

@Database(
    entities = [Note::class],
    version = 1,
    exportSchema = false
)
abstract class NotesDatabase : RoomDatabase() {
    abstract fun notesDao(): NotesDao

    companion object {
        const val DATABASE_NAME = "notes.db"
    }
}