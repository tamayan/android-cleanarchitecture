package com.example.cleanarchitecture.feature.domain.domain.news

import io.reactivex.Completable
import io.reactivex.Single

/**
 * Created by tamayan on 2017/12/09.
 */

interface NewsRepository {

    fun getNewsList(): Single<List<News>>

    fun insertOrUpdate(newsList: List<News>): Completable

    fun fetch(id: Int): Single<News>

    fun fetch(): Single<List<News>>
}