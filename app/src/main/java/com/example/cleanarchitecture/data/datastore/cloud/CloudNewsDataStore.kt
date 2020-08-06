package com.example.cleanarchitecture.data.datastore.cloud

import com.example.cleanarchitecture.data.datastore.NewsDataStore
import com.example.cleanarchitecture.data.datastore.cloud.client.ApiClient
import com.example.cleanarchitecture.data.entity.json.NewsRequestEntity
import com.example.cleanarchitecture.data.entity.json.NewsResponseListEntity
import com.example.cleanarchitecture.domain.entity.News
import io.reactivex.Observable
import io.reactivex.Single

/**
 * Created by MSnowRobin016 on 2017/11/25.
 */

class CloudNewsDataStore(private val client: ApiClient<NewsRequestEntity, NewsResponseListEntity>) : NewsDataStore {

    override fun getNewsList(): Single<List<News>> {
        return client
                .request(NewsRequestEntity())
                .flatMapObservable { Observable.fromIterable(it.list) }
                .map { News(id = it.id, title = it.title) }
                .toList()
    }
}