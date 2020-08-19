package com.example.cleanarchitecture.feature.domain.domain.news

import io.reactivex.Completable
import io.reactivex.Single

/**
 * Created by tamayan on 2017/12/09.
 */

interface NewsRepositoryInterface {

    fun save(newsList: List<News>): Completable

    fun find(id: Int): Single<News>

    fun findAll(): Single<List<News>>

    fun fetch(): Single<List<News>>
}