package com.example.cleanarchitecture.data.database

import com.example.cleanarchitecture.domain.domain.news.News
import io.reactivex.Completable
import io.reactivex.Single

interface NewsDataStoreInterface {

    fun save(news: News): Completable

    fun save(newsList: List<News>): Completable

    fun replaceAll(newsList: List<News>)

    fun find(id: Int): Single<News>

    fun findAll(): Single<List<News>>
}