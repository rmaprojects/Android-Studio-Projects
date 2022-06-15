package com.rmaproject.submissionandroidbeginnerdicoding.theme

import android.content.Context
import androidx.preference.PreferenceManager

class ThemeSharedPreferences(context:Context) {
    companion object {
        private const val THEME_SHARED_KEY = "THEME_SHARED_VALUE"
    }

    private val currentTheme = PreferenceManager.getDefaultSharedPreferences(context)
    var theme = currentTheme.getInt(THEME_SHARED_KEY, 0)
    set(value) = currentTheme.edit().putInt(THEME_SHARED_KEY, value).apply()
}