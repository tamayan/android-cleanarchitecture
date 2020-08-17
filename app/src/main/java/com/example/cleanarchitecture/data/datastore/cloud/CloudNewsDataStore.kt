package com.example.cleanarchitecture.data.datastore.cloud

import com.example.cleanarchitecture.data.datastore.NewsDataStore
import com.example.cleanarchitecture.domain.entity.News
import io.reactivex.Observable
import io.reactivex.Single

/**
 * Created by MSnowRobin016 on 2017/11/25.
 */

class CloudNewsDataStore(private val newsApi: NewsApi) : NewsDataStore {

    override fun getNewsList(): Single<List<News>> {
        return newsApi
                .getNewsList()
                .flatMapObservable { Observable.fromIterable(it) }
                .map { News(id = it.id, title = it.title, text = it.text) }
                .toList()
    }
}