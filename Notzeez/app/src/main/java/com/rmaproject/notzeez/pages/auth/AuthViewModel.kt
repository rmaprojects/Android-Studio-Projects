package com.rmaproject.notzeez.pages.auth

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.AuthResult
import com.rmaproject.notzeez.repository.MainRepository
import com.rmaproject.notzeez.util.Status
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AuthViewModel(
    private val repository: MainRepository
) : ViewModel() {

    private val _userRegistrationStatus = MutableLiveData<Status<AuthResult>>()
    val userRegistrationStatus: LiveData<Status<AuthResult>> = _userRegistrationStatus

    private val _userLoginStatus = MutableLiveData<Status<AuthResult>>()
    val userLoginStatus: LiveData<Status<AuthResult>> = _userLoginStatus

    fun signUpWithMailAndPassword(
        username: String,
        email: String,
        password: String
    ) {
        val error =
            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) "Not a Valid Email" else null

        error?.let {
            _userRegistrationStatus.postValue(Status.Error(it))
        }

        _userRegistrationStatus.postValue(Status.Loading())

        viewModelScope.launch(Dispatchers.Main) {
            val result = repository.signUpUser(username, email, password)
            _userRegistrationStatus.postValue(result)
        }
    }

    fun loginWithMailAndPassword(
        email: String,
        password: String
    ) {
        val error =
            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) "Not a Valid Email" else null
        error?.let {
            _userLoginStatus.postValue(Status.Error(error))
        }
        _userLoginStatus.postValue(Status.Loading())
        viewModelScope.launch(Dispatchers.Main) {
            val result = repository.loginUser(email, password)
            _userLoginStatus.postValue(result)
        }
    }
}