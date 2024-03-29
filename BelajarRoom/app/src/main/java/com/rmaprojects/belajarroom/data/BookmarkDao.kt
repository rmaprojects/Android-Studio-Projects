package com.rmaprojects.belajarroom.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.rmaprojects.belajarroom.data.entity.Bookmark
import kotlinx.coroutines.flow.Flow

@Dao
interface BookmarkDao {
    @Query("SELECT * FROM bookmark")
    fun getAllBookmarks(): Flow<List<Bookmark>>

    @Insert
    suspend fun insertBookmark(bookmark: Bookmark)

    @Delete
    suspend fun deleteBookmark(bookmark: Bookmark)

    @Query("DELETE FROM bookmark")
    suspend fun deleteAllFromBookmark()

    @Query("SELECT COUNT(*) FROM bookmark WHERE ayahNumber = :ayahNumber")
    suspend fun checkIfExist(ayahNumber: Int): List<Int>
}