package com.example.cleanarchitecture.data.database

import androidx.room.withTransaction
import com.example.cleanarchitecture.domain.domain.news.News
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class NewsDataStore(private val appDatabase: AppDatabase,
                    private val newsDao: NewsDao = appDatabase.newsDao()) : NewsDataStoreInterface {

    override fun find(id: Int): Flow<News> =
            newsDao.find(id).map { it.toNews() }

    override fun findAll(): Flow<List<News>> =
            newsDao.findAll().map { it.map { entity -> entity.toNews() } }

    override suspend fun save(newsList: List<News>) =
            appDatabase.withTransaction {
                newsDao.insertOrUpdate(newsList.map { it.toEntity() })
            }
}

private fun NewsEntity.toNews(): News =
        News(id, title, text)

private fun News.toEntity(): NewsEntity =
        NewsEntity(id, title, text)
