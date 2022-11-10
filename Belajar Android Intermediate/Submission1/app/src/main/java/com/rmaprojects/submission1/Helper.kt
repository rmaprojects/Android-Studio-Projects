package com.rmaprojects.submission1

import android.view.View
import com.google.android.material.snackbar.Snackbar
import com.rmaprojects.submission1.data.preferences.UserInfo

fun showSnackbar(rootLayout: View, message: String, duration: Int) {
    Snackbar.make(rootLayout, message, duration).show()
}

fun getToken() : String {
    return "Bearer " + UserInfo.token
}