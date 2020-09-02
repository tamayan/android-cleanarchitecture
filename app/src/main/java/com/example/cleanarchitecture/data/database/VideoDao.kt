package com.example.cleanarchitecture.data.database

import androidx.room.*

@Dao
abstract class VideoDao {

    @Query(value = "SELECT * FROM video WHERE id = :id")
    abstract suspend fun find(id: String): VideoEntity

    @Query(value = "SELECT * FROM video")
    abstract suspend fun findAll(): List<VideoEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insertOrUpdate(videos: List<VideoEntity>)

    @Query(value = "DELETE FROM video")
    abstract suspend fun deleteTable()

    @Transaction
    open suspend fun deleteAndInsert(videos: List<VideoEntity>) {
        deleteTable()
        insertOrUpdate(videos)
    }
}