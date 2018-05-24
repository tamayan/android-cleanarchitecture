package com.tamayan.cleanarchitecture.data.datastore.cloud

import com.tamayan.cleanarchitecture.data.datastore.NewsDataStore
import com.tamayan.cleanarchitecture.data.datastore.cloud.client.ApiClient
import com.tamayan.cleanarchitecture.data.entity.json.NewsRequestEntity
import com.tamayan.cleanarchitecture.data.entity.json.NewsResponseListEntity
import com.tamayan.cleanarchitecture.domain.entity.News
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