package com.rmaprojects.jetnotes.data.local

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import com.chibatching.kotpref.KotprefModel
import com.chibatching.kotpref.enumpref.enumOrdinalPref

object SharedPreference: KotprefModel() {
    var isDarkMode by booleanPref(false)
    var fontSize by enumOrdinalPref(FontSize.DEFAULT)
}

enum class FontSize(val text: String) {
    SMALL("Small"),
    DEFAULT("Default"),
    LARGE("Large");

    val titleStyle : TextStyle
        @Composable get() = when (this) {
            SMALL -> MaterialTheme.typography.titleSmall
            DEFAULT -> MaterialTheme.typography.titleLarge
            LARGE -> MaterialTheme.typography.headlineMedium
        }

    val contentStyle: TextStyle
        @Composable get() = when (this) {
            SMALL -> MaterialTheme.typography.bodySmall
            DEFAULT -> MaterialTheme.typography.bodyLarge
            LARGE -> MaterialTheme.typography.titleMedium
        }
}