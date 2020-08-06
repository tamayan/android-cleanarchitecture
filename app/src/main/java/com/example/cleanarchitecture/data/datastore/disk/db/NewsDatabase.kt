package com.example.cleanarchitecture.data.datastore.disk.db

import io.reactivex.Single

interface NewsDatabase {

    fun save(news: NewsEntity)

    fun save(newsList: List<NewsEntity>)

    fun replaceAll(newsList: List<NewsEntity>)

    fun find(id: Int): Single<NewsEntity>

    fun findAll(): Single<List<NewsEntity>>
}