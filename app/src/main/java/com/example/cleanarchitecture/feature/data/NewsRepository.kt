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

class NewsRepository(private val newsDataStore: NewsApiGatewayInterface,
                     private val newsDatabase: NewsDataStoreInterface) : NewsRepositoryInterface {

    override fun save(newsList: List<News>): Completable =
            newsDatabase.save(newsList)

    override fun find(id: Int): Single<News> =
            newsDatabase.find(id)

    override fun findAll(): Single<List<News>> =
            newsDataStore
                    .getNewsList()
                    // APIからの取得に成功後DBに保存
                    .doOnSuccess { newsDatabase.save(it) }
                    // APIからの取得に失敗した場合、DBのNewsを返す
                    .onErrorResumeNext { newsDatabase.findAll() }
}