package com.example.cleanarchitecture.feature.data

import com.example.cleanarchitecture.feature.data.api.NewsApiGatewayInterface
import com.example.cleanarchitecture.feature.data.database.NewsDataStoreInterface
import com.example.cleanarchitecture.feature.domain.domain.news.News
import com.example.cleanarchitecture.feature.domain.domain.news.NewsRepositoryInterface
import io.reactivex.Completable
import io.reactivex.Single

/**
 * Created by tamayan on 2017/12/09.
 */

class NewsRepository(private val apiGateway: NewsApiGatewayInterface,
                     private val dataStore: NewsDataStoreInterface) : NewsRepositoryInterface {

    override fun save(newsList: List<News>): Completable =
            dataStore.save(newsList)

    override fun find(id: Int): Single<News> =
            dataStore.find(id)

    override fun findAll(): Single<List<News>> =
            apiGateway
                    .getNewsList()
                    // APIからの取得に成功後DBに保存
                    .doOnSuccess { dataStore.save(it) }
                    // APIからの取得に失敗した場合、DBのNewsを返す
                    .onErrorResumeNext { dataStore.findAll() }
}