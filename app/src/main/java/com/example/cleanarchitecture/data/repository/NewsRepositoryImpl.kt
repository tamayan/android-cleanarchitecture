package com.example.cleanarchitecture.data.repository

import com.example.cleanarchitecture.data.datastore.NewsDataStore
import com.example.cleanarchitecture.data.datastore.disk.db.NewsDatabase
import com.example.cleanarchitecture.data.datastore.disk.db.NewsEntity
import com.example.cleanarchitecture.di.ApplicationModule
import com.example.cleanarchitecture.domain.entity.News
import com.example.cleanarchitecture.domain.repository.NewsRepository
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import javax.inject.Inject

/**
 * Created by tamayan on 2017/12/09.
 */

class NewsRepositoryImpl @Inject constructor(
        @ApplicationModule.CloudNewsDataStore private val newsDataStore: NewsDataStore,
        @ApplicationModule.NewsRoomDatabase private val newsDatabase: NewsDatabase
) : NewsRepository {

    override fun getNewsList(): Single<List<News>> =
            newsDataStore.getNewsList()

    override fun insertOrUpdate(newsList: List<News>): Completable =
            newsDatabase.save(newsList.map { NewsEntity(it.id, it.title, it.text) })

    override fun fetch(id: Int): Single<News> =
            newsDatabase
                    .find(id)
                    .map { News(it.id, it.title, it.text) }

    override fun fetch(): Single<List<News>> =
            newsDatabase.findAll()
                    .flatMapObservable { Observable.fromIterable(it) }
                    .map { News(it.id, it.title, it.text) }
                    .toList()
}