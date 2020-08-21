package com.example.cleanarchitecture.data.database

import com.example.cleanarchitecture.domain.domain.news.News

class NewsDataStore(appDatabase: AppDatabase) : NewsDataStoreInterface {

    private val newsDao = appDatabase.newsDao()

    override suspend fun save(news: News) =
            newsDao.insertOrUpdate(mapToNewsEntity(news))

    override suspend fun save(newsList: List<News>) =
            newsDao.insertOrUpdate(newsList.map { mapToNewsEntity(it) })

    override suspend fun replaceAll(newsList: List<News>) =
            newsDao.deleteAndInsert(newsList.map { mapToNewsEntity(it) })

    override suspend fun find(id: Int): News =
            mapToNews(newsDao.find(id))

    override suspend fun findAll(): List<News> =
            newsDao.findAll().map { mapToNews(it) }

    private fun mapToNewsEntity(news: News): NewsEntity =
            NewsEntity(news.id, news.title, news.text)

    private fun mapToNews(newsEntity: NewsEntity): News =
            News(newsEntity.id, newsEntity.title, newsEntity.text)
}