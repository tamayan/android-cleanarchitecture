package com.example.cleanarchitecture.data.database

import androidx.room.withTransaction
import com.example.cleanarchitecture.domain.domain.news.News

class NewsDataStore(private val appDatabase: AppDatabase) : NewsDataStoreInterface {

//    private val newsDao = appDatabase.newsDao()

    override suspend fun save(news: News) {
        appDatabase.withTransaction {
            appDatabase.newsDao().insertOrUpdate(mapToNewsEntity(news))
        }
    }

    override suspend fun save(newsList: List<News>) {
        appDatabase.withTransaction {
            appDatabase.newsDao().insertOrUpdate(newsList.map { mapToNewsEntity(it) })
        }
    }

    override suspend fun replaceAll(newsList: List<News>) {
        appDatabase.withTransaction {
            appDatabase.newsDao().deleteAndInsert(newsList.map { mapToNewsEntity(it) })
        }
    }

    override suspend fun find(id: Int): News =
            mapToNews(appDatabase.newsDao().find(id))

    override suspend fun findAll(): List<News> =
            appDatabase.newsDao().findAll().map { mapToNews(it) }

    private fun mapToNewsEntity(news: News): NewsEntity =
            NewsEntity(news.id, news.title, news.text)

    private fun mapToNews(newsEntity: NewsEntity): News =
            News(newsEntity.id, newsEntity.title, newsEntity.text)
}