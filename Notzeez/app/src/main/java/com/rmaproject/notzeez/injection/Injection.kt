package com.rmaproject.notzeez.injection

import android.content.Context
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import com.rmaproject.notzeez.data.NotesDatabase
import com.rmaproject.notzeez.repository.MainRepository

object Injection {
    fun provideRepository(context: Context): MainRepository {
        val auth = Firebase.auth
        val database = Firebase.database
        val storage = Firebase.storage
        val noteDatabase = NotesDatabase.getInstance(context).dao()
        return MainRepository(auth, database, storage, noteDatabase)
    }
}