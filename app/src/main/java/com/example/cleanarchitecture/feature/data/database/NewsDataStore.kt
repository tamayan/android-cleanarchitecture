package com.example.cleanarchitecture.feature.data.database

import com.example.cleanarchitecture.feature.domain.domain.news.News
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single

class NewsDataStore(private val appDatabase: AppDatabase) : NewsDataStoreInterface {

    override fun save(news: News): Completable =
            Completable.create { emitter ->
                try {
                    appDatabase
                            .newsDao()
                            .insertOrUpdate(toNewsEntity(news))
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
                            .insertOrUpdate(newsList.map { toNewsEntity(it) })
                    emitter.onComplete()
                } catch (e: Exception) {
                    emitter.onError(e)
                }
            }

    override fun replaceAll(newsList: List<News>) {
        appDatabase.runInTransaction {
            appDatabase
                    .newsDao()
                    .deleteAndInsert(newsList.map { toNewsEntity(it) })
        }
    }

    override fun find(id: Int): Single<News> =
            appDatabase
                    .newsDao()
                    .find(id)
                    .map { toNews(it) }

    override fun findAll(): Single<List<News>> =
            appDatabase
                    .newsDao()
                    .findAll()
                    .flatMapObservable { Observable.fromIterable(it) }
                    .map { toNews(it) }
                    .toList()

    private fun toNewsEntity(news: News): NewsEntity =
            NewsEntity(news.id, news.title, news.text)

    private fun toNews(newsEntity: NewsEntity): News =
            News(newsEntity.id, newsEntity.title, newsEntity.text)
}