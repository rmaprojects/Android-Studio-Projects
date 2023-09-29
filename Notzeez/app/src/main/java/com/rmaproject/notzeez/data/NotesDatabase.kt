package com.rmaproject.notzeez.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.rmaproject.notzeez.model.Note

@Database(entities = [Note::class], version = 1)
abstract class NotesDatabase : RoomDatabase() {
    abstract fun dao(): NotesDao
    
    companion object {
        @Volatile private var INSTANCE: NotesDatabase? = null
        fun getInstance(context: Context) : NotesDatabase {
            return INSTANCE?: synchronized(this) {
                INSTANCE ?: buildDatabase(context).also {
                    INSTANCE = it
                }
            }
        }
        private fun buildDatabase(context: Context): NotesDatabase {
            return Room.databaseBuilder(context.applicationContext, NotesDatabase::class.java, "notes.db")
                .fallbackToDestructiveMigration()
                .build()
        }

    }
}