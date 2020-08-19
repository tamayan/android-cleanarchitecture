package com.example.cleanarchitecture.feature.infrastructure

import com.example.cleanarchitecture.feature.domain.domain.news.News
import com.example.cleanarchitecture.feature.domain.domain.news.NewsRepositoryInterface
import com.example.cleanarchitecture.feature.infrastructure.local.NewsDatabase
import com.example.cleanarchitecture.feature.infrastructure.local.room.NewsEntity
import com.example.cleanarchitecture.feature.infrastructure.remote.NewsDataStore
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single

/**
 * Created by tamayan on 2017/12/09.
 */

class NewsRepositoryImpl(private val newsDataStore: NewsDataStore,
                         private val newsDatabase: NewsDatabase) : NewsRepositoryInterface {

    override fun save(newsList: List<News>): Completable =
            newsDatabase.save(newsList.map { NewsEntity(it.id, it.title, it.text) })

    override fun find(id: Int): Single<News> =
            newsDatabase
                    .find(id)
                    .map { News(it.id, it.title, it.text) }

    override fun findAll(): Single<List<News>> =
            newsDatabase
                    .findAll()
                    .flatMapObservable { Observable.fromIterable(it) }
                    .map { News(it.id, it.title, it.text) }
                    .toList()

    override fun fetch(): Single<List<News>> =
            newsDataStore
                    .getNewsList()
                    .flatMapObservable { Observable.fromIterable(it) }
                    .map { News(it.id, it.title, it.text) }
                    .toList()
}