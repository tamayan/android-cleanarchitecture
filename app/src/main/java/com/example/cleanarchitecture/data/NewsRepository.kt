package com.example.cleanarchitecture.data

import com.example.cleanarchitecture.data.api.NewsApiGatewayInterface
import com.example.cleanarchitecture.data.database.NewsDataStoreInterface
import com.example.cleanarchitecture.domain.domain.news.News
import com.example.cleanarchitecture.domain.domain.news.NewsRepositoryInterface
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow

/**
 * Created by tamayan on 2017/12/09.
 */

class NewsRepository(private val apiGateway: NewsApiGatewayInterface,
                     private val dataStore: NewsDataStoreInterface) : NewsRepositoryInterface {

    override suspend fun find(id: Int): News =
            dataStore.find(id)

    @ExperimentalCoroutinesApi
    override fun findAll(): Flow<List<News>> =
            flow {
                // APIから取得
                val newsList = apiGateway.getNewsList()
                // DBに保存
                dataStore.save(newsList)
                emit(newsList)
            }.catch {
                // APIから取得に失敗した場合、DBから取得
                emitAll(dataStore.findAll())
            }
}