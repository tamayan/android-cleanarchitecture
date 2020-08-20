package com.example.cleanarchitecture.feature.data.api

import com.example.cleanarchitecture.feature.domain.domain.news.News
import io.reactivex.Observable
import io.reactivex.Single

/**
 * Created by MSnowRobin016 on 2017/11/25.
 */

class NewsApiGateway(private val newsApi: NewsApi) : NewsApiGatewayInterface {

    override fun getNewsList(): Single<List<News>> =
            newsApi
                    .fetch()
                    .flatMapObservable { Observable.fromIterable(it) }
                    .map { News(it.id, it.title, it.text) }
                    .toList()
}