package com.example.cleanarchitecture.feature.infrastructure.local.room

import com.example.cleanarchitecture.feature.infrastructure.local.NewsDatabase
import io.reactivex.Completable
import io.reactivex.Single

class NewsRoomDatabase(private val appDatabase: AppDatabase) : NewsDatabase {

    override fun save(news: NewsEntity): Completable =
            Completable.create { emitter ->
                try {
                    appDatabase.newsDao().insertOrUpdate(news)
                    emitter.onComplete()
                } catch (e: Exception) {
                    emitter.onError(e)
                }
            }

    override fun save(newsList: List<NewsEntity>): Completable =
            Completable.create { emitter ->
                try {
                    appDatabase.newsDao().insertOrUpdate(newsList)
                    emitter.onComplete()
                } catch (e: Exception) {
                    emitter.onError(e)
                }
            }

    override fun replaceAll(newsList: List<NewsEntity>) {
        appDatabase.runInTransaction {
            appDatabase.newsDao().deleteAndInsert(newsList)
        }
    }

    override fun find(id: Int): Single<NewsEntity> =
            appDatabase.newsDao().find(id)

    override fun findAll(): Single<List<NewsEntity>> =
            appDatabase.newsDao().findAll()
}