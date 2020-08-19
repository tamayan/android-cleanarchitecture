package com.example.cleanarchitecture.feature.gateway.local.room

import com.example.cleanarchitecture.feature.domain.domain.news.News
import com.example.cleanarchitecture.feature.gateway.local.NewsDatabase
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single

class NewsRoomDatabase(private val appDatabase: AppDatabase) : NewsDatabase {

    override fun save(news: News): Completable =
            Completable.create { emitter ->
                try {
                    appDatabase
                            .newsDao()
                            .insertOrUpdate(NewsEntity(news.id, news.title, news.text))
                    emitter.onComplete()
                } catch (e: Exception) {
                    emitter.onError(e)
                }
            }


    override fun save(newsList: List<News>): Completable =
            Completable.create { emitter ->
                try {
                    appDatabase
                            .newsDao()
                            .insertOrUpdate(newsList.map { NewsEntity(it.id, it.title, it.text) })
                    emitter.onComplete()
                } catch (e: Exception) {
                    emitter.onError(e)
                }
            }

    override fun replaceAll(newsList: List<News>) {
        appDatabase.runInTransaction {
            appDatabase
                    .newsDao()
                    .deleteAndInsert(newsList.map { NewsEntity(it.id, it.title, it.text) })
        }
    }

    override fun find(id: Int): Single<News> =
            appDatabase
                    .newsDao()
                    .find(id)
                    .map { News(it.id, it.title, it.text) }

    override fun findAll(): Single<List<News>> =
            appDatabase
                    .newsDao()
                    .findAll()
                    .flatMapObservable { Observable.fromIterable(it) }
                    .map { News(it.id, it.title, it.text) }
                    .toList()
}