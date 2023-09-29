package com.rmaprojects.jetnotes.di

import android.app.Application
import androidx.room.Room
import com.rmaprojects.jetnotes.data.local.database.NotesDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideNotesDatabase(
        app: Application
    ) : NotesDatabase {
        return Room.databaseBuilder(
            app.applicationContext,
            NotesDatabase::class.java,
            NotesDatabase.DATABASE_NAME
        ).build()
    }
}