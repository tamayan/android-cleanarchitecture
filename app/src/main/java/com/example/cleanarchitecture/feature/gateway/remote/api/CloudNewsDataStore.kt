package com.example.cleanarchitecture.feature.gateway.remote.api

import com.example.cleanarchitecture.feature.domain.domain.news.News
import com.example.cleanarchitecture.feature.gateway.remote.NewsDataStore
import io.reactivex.Observable
import io.reactivex.Single

/**
 * Created by MSnowRobin016 on 2017/11/25.
 */

class CloudNewsDataStore(private val newsApi: NewsApi) : NewsDataStore {

    override fun getNewsList(): Single<List<News>> =
            newsApi
                    .getNewsList()
                    .flatMapObservable { Observable.fromIterable(it) }
                    .map { News(it.id, it.title, it.text) }
                    .toList()
}