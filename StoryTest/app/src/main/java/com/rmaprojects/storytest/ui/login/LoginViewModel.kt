package com.rmaprojects.storytest.ui.login

import androidx.lifecycle.ViewModel
import com.rmaprojects.storytest.data.source.remote.service.ApiInterface

class LoginViewModel(
    private val apiInterface: ApiInterface
): ViewModel() {


    fun login(
        email: String,
        password: String
    ) {

    }

}