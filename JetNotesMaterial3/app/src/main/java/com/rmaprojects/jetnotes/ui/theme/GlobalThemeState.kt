package com.rmaprojects.jetnotes.ui.theme

import androidx.compose.runtime.mutableStateOf
import com.rmaprojects.jetnotes.data.local.SharedPreference

val isDarkMode = mutableStateOf(SharedPreference.isDarkMode)
val currentFontSize = mutableStateOf(SharedPreference.fontSize)