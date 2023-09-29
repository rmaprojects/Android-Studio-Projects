package com.rmaprojects.storytest.data.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.rmaprojects.storytest.data.source.remote.service.ApiInterface
import com.rmaprojects.storytest.di.Injection
import com.rmaprojects.storytest.ui.login.LoginViewModel
import com.rmaprojects.storytest.ui.register.RegisterViewModel

class ViewModelFactory(
    private val api: ApiInterface
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        when {
            modelClass.isAssignableFrom(LoginViewModel::class.java) -> LoginViewModel(api) as T
            modelClass.isAssignableFrom(RegisterViewModel::class.java) -> RegisterViewModel(api) as T
            else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
        }

    companion object {
        @Volatile
        private var INSTANCE: ViewModelFactory? = null
        fun getInstance(): ViewModelFactory = INSTANCE ?: synchronized(this) {
            INSTANCE ?: ViewModelFactory(Injection.provideApiService())
        }.also { INSTANCE = it }
    }
}