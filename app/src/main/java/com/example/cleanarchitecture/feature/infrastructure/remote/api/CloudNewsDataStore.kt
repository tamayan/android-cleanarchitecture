package com.example.cleanarchitecture.feature.infrastructure.remote.api

import com.example.cleanarchitecture.feature.infrastructure.remote.NewsDataStore
import io.reactivex.Single

/**
 * Created by MSnowRobin016 on 2017/11/25.
 */

class CloudNewsDataStore(private val newsApi: NewsApi) : NewsDataStore {

    override fun getNewsList(): Single<List<NewsJson>> =
            newsApi.getNewsList()
}