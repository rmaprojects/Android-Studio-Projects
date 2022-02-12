package com.rmaproject.timepicker.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tbl_alarm")
data class AlarmModel(
    @PrimaryKey(autoGenerate = true) val id:Int? = null,
    @ColumnInfo(name = "nama_alarm") val alarmName:String? = "",
    @ColumnInfo(name = "jam") val hour:String? = null,
    @ColumnInfo(name = "menit") val min:String? = null
)
