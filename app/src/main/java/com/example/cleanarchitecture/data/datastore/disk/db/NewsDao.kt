package com.example.cleanarchitecture.data.datastore.disk.db

import androidx.room.*
import io.reactivex.Single

@Dao
interface NewsDao {

    @Query(value = "SELECT * FROM news WHERE id = :id")
    fun find(id: Int): Single<NewsEntity>

    @Query(value = "SELECT * FROM news")
    fun findAll(): Single<List<NewsEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertOrUpdate(news: NewsEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertOrUpdate(newsList: List<NewsEntity>)

    @Delete
    fun delete(news: NewsEntity)

    @Query(value = "DELETE FROM news")
    fun deleteTable()

    @Transaction
    fun deleteAndInsert(newsList: List<NewsEntity>) {
        deleteTable()
        insertOrUpdate(newsList)
    }
}