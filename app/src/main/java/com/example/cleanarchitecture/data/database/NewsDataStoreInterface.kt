package com.example.cleanarchitecture.data.database

import com.example.cleanarchitecture.domain.domain.news.News
import kotlinx.coroutines.flow.Flow

interface NewsDataStoreInterface {

    fun find(id: Int): Flow<News>

    fun findAll(): Flow<List<News>>

    suspend fun save(newsList: List<News>)
}