package com.rmaprojects.jadwalsholatapp.data

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.rmaprojects.jadwalsholatapp.data.repository.Repository
import com.rmaprojects.jadwalsholatapp.di.Injection
import com.rmaprojects.jadwalsholatapp.pages.MainActivityViewModel

class ViewModelFactory(
    private val repository: Repository
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainActivityViewModel::class.java)) {
            return MainActivityViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown Class: ${modelClass.name}")
    }

    companion object {
        @Volatile
        private var INSTANCE:ViewModelFactory? = null
        fun getInstance() : ViewModelFactory = INSTANCE?: synchronized(this) {
            INSTANCE?: ViewModelFactory(Injection.provideRepository())
        }.also { INSTANCE = it }
    }
}