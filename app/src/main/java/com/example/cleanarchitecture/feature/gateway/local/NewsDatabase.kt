package com.example.cleanarchitecture.feature.gateway.local

import com.example.cleanarchitecture.feature.domain.domain.news.News
import io.reactivex.Completable
import io.reactivex.Single

interface NewsDatabase {

    fun save(news: News): Completable

    fun save(newsList: List<News>): Completable

    fun replaceAll(newsList: List<News>)

    fun find(id: Int): Single<News>

    fun findAll(): Single<List<News>>
}