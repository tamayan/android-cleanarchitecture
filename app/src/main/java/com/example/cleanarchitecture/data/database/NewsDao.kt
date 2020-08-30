package com.example.cleanarchitecture.data.database

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
abstract class NewsDao {

    @Query(value = "SELECT * FROM news WHERE id = :id")
    abstract suspend fun find(id: Int): NewsEntity

    @Query(value = "SELECT * FROM news ORDER BY id")
    abstract fun findAll(): Flow<List<NewsEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insertOrUpdate(news: NewsEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insertOrUpdate(newsList: List<NewsEntity>)

    @Query(value = "DELETE FROM news")
    abstract suspend fun deleteTable()

    @Transaction
    open suspend fun deleteAndInsert(newsList: List<NewsEntity>) {
        deleteTable()
        insertOrUpdate(newsList)
    }
}