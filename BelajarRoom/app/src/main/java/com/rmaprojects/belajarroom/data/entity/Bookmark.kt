package com.rmaprojects.belajarroom.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "bookmark")
data class Bookmark(
    val surahName: String? = "",
    val ayahNumber: Int? = 0,
    val surahNumber: Int? = 0,
    @PrimaryKey(autoGenerate = true) val id: Int? = null,
    val createdAt: Long = System.currentTimeMillis(),
)
