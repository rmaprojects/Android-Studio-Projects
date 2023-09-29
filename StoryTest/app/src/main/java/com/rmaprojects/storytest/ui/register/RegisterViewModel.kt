package com.rmaprojects.storytest.ui.register

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rmaprojects.storytest.data.source.remote.service.ApiInterface
import com.rmaprojects.storytest.ui.register.state.RegisterState
import kotlinx.coroutines.launch

class RegisterViewModel(
    private val api: ApiInterface
) : ViewModel() {

    private val _loadingStatus = MutableLiveData<RegisterState>(null)
    val loadingStatus: LiveData<RegisterState> = _loadingStatus

    fun register(
        name: String,
        email: String,
        password: String
    ) {
        viewModelScope.launch {
            _loadingStatus.postValue(
                RegisterState.Loading
            )
            try {
                val result = api.registerUser(
                    name,
                    email,
                    password
                )
                if (!result.error) {
                    _loadingStatus.postValue(
                        RegisterState.Success
                    )
                }
            } catch (e: Exception) {
                _loadingStatus.postValue(
                    RegisterState.Error(e.message.toString())
                )
            }
        }
    }
}