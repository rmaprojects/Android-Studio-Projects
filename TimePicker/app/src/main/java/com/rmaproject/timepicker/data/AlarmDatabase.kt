package com.rmaproject.timepicker.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.rmaproject.timepicker.model.AlarmModel

@Database(entities = [AlarmModel::class], version = 1)
abstract class AlarmDatabase : RoomDatabase() {
    abstract fun alarmDatabase():AlarmDAO

    companion object {
        @Volatile private var INSTANCE: AlarmDatabase? = null
        fun getInstance(context: Context): AlarmDatabase {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: buildDatabase(context).also {
                    INSTANCE = it
                }
            }
        }

        private fun buildDatabase(context: Context): AlarmDatabase {
            return Room.databaseBuilder(context.applicationContext, AlarmDatabase::class.java, "bookmark.db")
                .fallbackToDestructiveMigration()
                .build()
        }
    }
}