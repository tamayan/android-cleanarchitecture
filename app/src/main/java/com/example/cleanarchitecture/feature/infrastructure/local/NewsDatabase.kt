package com.example.cleanarchitecture.feature.infrastructure.local

import com.example.cleanarchitecture.feature.infrastructure.local.room.NewsEntity
import io.reactivex.Completable
import io.reactivex.Single

interface NewsDatabase {

    fun save(news: NewsEntity): Completable

    fun save(newsList: List<NewsEntity>): Completable

    fun replaceAll(newsList: List<NewsEntity>)

    fun find(id: Int): Single<NewsEntity>

    fun findAll(): Single<List<NewsEntity>>
}