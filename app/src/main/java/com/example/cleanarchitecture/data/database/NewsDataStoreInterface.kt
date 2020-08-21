package com.example.cleanarchitecture.data.database

import com.example.cleanarchitecture.domain.domain.news.News

interface NewsDataStoreInterface {

    suspend fun save(news: News)

    suspend fun save(newsList: List<News>)

    suspend fun replaceAll(newsList: List<News>)

    suspend fun find(id: Int): News

    suspend fun findAll(): List<News>
}