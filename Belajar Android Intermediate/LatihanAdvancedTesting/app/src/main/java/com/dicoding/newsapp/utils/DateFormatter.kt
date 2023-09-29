package com.dicoding.newsapp.utils

import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.TimeZone

object DateFormatter {

    fun formatDate(
        currentDate: String,
        targetTimeZone: String
    ) : String {
        // Convert to ISO
        val instant = Instant.parse(currentDate)
        val formatter = DateTimeFormatter.ofPattern("dd MMM yyyy | HH:mm")
            .withZone(ZoneId.of(targetTimeZone))

        return formatter.format(instant)
    }
}