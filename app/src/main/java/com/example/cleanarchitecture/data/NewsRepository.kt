package com.example.cleanarchitecture.data

import com.example.cleanarchitecture.data.api.NewsApiGatewayInterface
import com.example.cleanarchitecture.data.database.NewsDataStoreInterface
import com.example.cleanarchitecture.domain.domain.news.News
import com.example.cleanarchitecture.domain.domain.news.NewsRepositoryInterface

/**
 * Created by tamayan on 2017/12/09.
 */

class NewsRepository(private val apiGateway: NewsApiGatewayInterface,
                     private val dataStore: NewsDataStoreInterface) : NewsRepositoryInterface {

    override suspend fun save(newsList: List<News>) =
            dataStore.save(newsList)

    override suspend fun find(id: Int): News =
            dataStore.find(id)

    override suspend fun findAll(): List<News> {
        return try {
            val newsList = apiGateway.getNewsList()
            dataStore.save(newsList)
            newsList
        } catch (exception: Exception) {
            dataStore.findAll()
        }
    }
}