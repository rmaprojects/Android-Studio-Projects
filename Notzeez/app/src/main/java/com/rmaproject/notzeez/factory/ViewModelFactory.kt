package com.rmaproject.notzeez.factory

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.rmaproject.notzeez.injection.Injection
import com.rmaproject.notzeez.pages.addnote.AddNoteViewModel
import com.rmaproject.notzeez.pages.auth.AuthViewModel
import com.rmaproject.notzeez.pages.favourites.FavouriteViewModel
import com.rmaproject.notzeez.pages.profile.ProfileViewModel
import com.rmaproject.notzeez.repository.MainRepository

class ViewModelFactory(private val repository: MainRepository) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(AddNoteViewModel::class.java) -> AddNoteViewModel(repository) as T
            modelClass.isAssignableFrom(AuthViewModel::class.java) -> AuthViewModel(repository) as T
            modelClass.isAssignableFrom(ProfileViewModel::class.java) -> ProfileViewModel(repository) as T
            modelClass.isAssignableFrom(FavouriteViewModel::class.java) -> FavouriteViewModel(repository) as T
            else -> throw Exception("Unknown Class: ${modelClass.name}")
        }
    }

    companion object {
        @Volatile
        private var INSTANCE: ViewModelFactory?= null
        fun getInstance(context: Context): ViewModelFactory = INSTANCE ?: synchronized(this) {
            INSTANCE?: ViewModelFactory(Injection.provideRepository(context))
        }.also { INSTANCE = it }
    }
}