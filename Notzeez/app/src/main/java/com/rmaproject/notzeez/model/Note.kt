package com.rmaproject.notzeez.model

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.google.firebase.database.ServerValue.TIMESTAMP

@Entity(tableName = "notes")
data class Note(
    var title: String? = "",
    var content: String? = "",
    var noteId: String? = "",
    @Ignore var updatedAt: Any? = TIMESTAMP,
    @Ignore var createdAt: Any? = TIMESTAMP,
    @PrimaryKey(autoGenerate = true)
    var id: Int? = 0,
)