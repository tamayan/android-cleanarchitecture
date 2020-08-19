package com.example.cleanarchitecture.feature.infrastructure.remote

import com.example.cleanarchitecture.feature.infrastructure.remote.api.NewsJson
import io.reactivex.Single

/**
 * Created by tamayan on 2017/11/23.
 */

interface NewsDataStore {

    fun getNewsList(): Single<List<NewsJson>>
}