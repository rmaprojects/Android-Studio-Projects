package com.rmaprojects.signupsigninsupabase.preferences

import android.content.Context
import androidx.preference.PreferenceManager

class SignedInPreferences (context: Context) {

    companion object {
        const val SIGNEDKEY = "KEYSINGIN"
    }

    private val signIn = PreferenceManager.getDefaultSharedPreferences(context)
    var isSignedIn = signIn.getBoolean(SIGNEDKEY, false)
        set(value) = signIn.edit().putBoolean(SIGNEDKEY,value).apply()
}