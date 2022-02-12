package com.rmaproject.timepicker.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.rmaproject.timepicker.model.AlarmModel
import kotlinx.coroutines.flow.Flow

@Dao
interface AlarmDAO {
    //Buat nyimpen data alarm
    @Insert
    suspend fun simpanAlarm(alarmModel: AlarmModel)

    //Buat ngehapus data alarm
    @Delete
    suspend fun hapusAlarm(alarmModel: AlarmModel)

    //Buat nampilin alarm
    @Query("SELECT * FROM tbl_alarm WHERE id = :id ORDER BY id ASC")
    fun getAlarm(id:Int?) : Flow<List<AlarmModel>>

}