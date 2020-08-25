package com.example.cleanarchitecture.data

import com.example.cleanarchitecture.data.api.NewsApiGatewayInterface
import com.example.cleanarchitecture.data.database.NewsDataStoreInterface
import com.example.cleanarchitecture.domain.domain.news.News
import com.example.cleanarchitecture.domain.domain.news.NewsRepositoryInterface
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import timber.log.Timber

/**
 * Created by tamayan on 2017/12/09.
 */

class NewsRepository(private val apiGateway: NewsApiGatewayInterface,
                     private val dataStore: NewsDataStoreInterface) : NewsRepositoryInterface {

    @ExperimentalCoroutinesApi
    override suspend fun find(id: Int): Flow<News> =
            dataStore.find(id).flowOn(Dispatchers.IO)

    @ExperimentalCoroutinesApi
    override suspend fun findAll(): Flow<List<News>> =
            flow {
                // APIから取得
                val newsList = apiGateway.getNewsList()
                // DBに保存
                dataStore.save(newsList)
                emit(newsList)
            }.catch {
                Timber.e(it)
                // APIから取得に失敗した場合、DBから取得
                emitAll(dataStore.findAll())
            }.flowOn(Dispatchers.IO)
}