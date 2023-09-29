package com.rmaproject.notzeez.util

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.core.content.getSystemService
import androidx.fragment.app.Fragment
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

fun getCurrentUser() = Firebase.auth.currentUser

fun createSnackBar(rootView: View, message: String, duration: Int): Snackbar {
    return Snackbar.make(rootView, message, duration)
}

val database by lazy {
    Firebase.database
}

fun createAlertDialog(context: Context, title: String, message: String): MaterialAlertDialogBuilder {
    return MaterialAlertDialogBuilder(context)
        .setTitle(title)
        .setMessage(message)
}

fun Fragment.showOrHideKeyBoard(isKeyboardShowed: Boolean, view: View, flags: Int = 0) {
    val ime = requireActivity().getSystemService<InputMethodManager>()
    if (isKeyboardShowed) {
        ime!!.showSoftInput(view, flags)
    } else {
        ime!!.hideSoftInputFromWindow(view.windowToken, flags)
    }
}

class Keyboard {
    companion object {
        const val SHOW = true
        const val HIDE = false
    }
}