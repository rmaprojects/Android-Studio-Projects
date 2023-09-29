package com.rmaprojects.belajarroom.data

import androidx.room.Dao
import androidx.room.Query
import com.rmaprojects.belajarroom.data.entity.Juz
import kotlinx.coroutines.flow.Flow

@Dao
interface QoranDao {
    @Query("SELECT * FROM juz")
    fun getAllFromJuz(): Flow<List<Juz>>
}