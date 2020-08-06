package com.example.cleanarchitecture.data.datastore.disk.db

import androidx.room.*
import io.reactivex.Single

@Dao
abstract class NewsDao {

    @Query(value = "SELECT * FROM news WHERE id (:id)")
    abstract fun find(id: Int): Single<NewsEntity>

    @Query(value = "SELECT * FROM news")
    abstract fun findAll(): Single<List<NewsEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertOrUpdate(news: NewsEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertOrUpdate(newsList: List<NewsEntity>)

    @Delete
    abstract fun delete(news: NewsEntity)

    @Query(value = "DELETE FROM news")
    abstract fun deleteTable()

    @Transaction
    open fun deleteAndInsert(newsList: List<NewsEntity>) {
        deleteTable()
        insertOrUpdate(newsList)
    }
}