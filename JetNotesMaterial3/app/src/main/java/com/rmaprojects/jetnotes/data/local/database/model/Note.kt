package com.rmaprojects.jetnotes.data.local.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "note")
data class Note(
    @PrimaryKey(autoGenerate = true) val id: Int?,
    val title: String? = "Untitled",
    val content: String? = "",
    val time: Long = System.currentTimeMillis()
)
