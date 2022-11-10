package com.rmaprojects.submission1.pages.auth

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rmaprojects.submission1.data.preferences.UserInfo
import com.rmaprojects.submission1.data.repository.StoriesRepository
import kotlinx.coroutines.launch

class AuthViewModel(private val repository: StoriesRepository) : ViewModel() {

    private val _isLoginLoading = MutableLiveData<Boolean?>()
    val isLoginLoading : LiveData<Boolean?> = _isLoginLoading

    private val _isRegisterLoading = MutableLiveData<Boolean?>()
    val isRegisterLoading : LiveData<Boolean?> = _isRegisterLoading

    private val _isLoginSuccess = MutableLiveData<Boolean>()
    val isLoginSuccess : LiveData<Boolean> = _isLoginSuccess

    private val _isRegisterSuccess = MutableLiveData<Boolean>()
    val isRegisterSuccess : LiveData<Boolean> = _isRegisterSuccess

    fun loginUser(email : String, password: String) {
        _isLoginLoading.postValue(true)
        viewModelScope.launch {
            try {
                val response = repository.loginUser(email, password)
                response.loginResult?.let {
                    UserInfo.name = it.name.toString()
                    UserInfo.token = it.token.toString()
                    UserInfo.userId = it.userId.toString()
                }
                _isLoginLoading.postValue(false)
                _isLoginSuccess.postValue(true)
            } catch (e: Exception) {
                _isLoginLoading.postValue(null)
                _isLoginSuccess.postValue(false)
                Log.d("ERROR", e.toString())
            }
        }
    }

    fun registerUser(name: String, email: String, password: String) {
        _isRegisterLoading.postValue(true)
        viewModelScope.launch {
            try {
                val response = repository.registerUser(name, email, password)
                if (response.error) {
                    _isRegisterSuccess.postValue(false)
                } else {
                    _isRegisterSuccess.postValue(true)
                }
                _isRegisterLoading.postValue(false)
            } catch (e: Exception) {
                _isRegisterLoading.postValue(null)
                _isRegisterSuccess.postValue(false)
                Log.d("ERROR", e.toString())
            }
        }
    }
}