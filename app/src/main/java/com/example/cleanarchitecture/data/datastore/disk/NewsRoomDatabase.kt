package com.example.cleanarchitecture.data.datastore.disk

import com.example.cleanarchitecture.data.datastore.disk.db.AppDatabase
import com.example.cleanarchitecture.data.datastore.disk.db.NewsDao
import com.example.cleanarchitecture.data.datastore.disk.db.NewsDatabase
import com.example.cleanarchitecture.data.datastore.disk.db.NewsEntity
import io.reactivex.Single

class NewsRoomDatabase(private val appDatabase: AppDatabase,
                       private val newsDao: NewsDao) : NewsDatabase {

    override fun save(news: NewsEntity) = newsDao.insertOrUpdate(news)

    override fun save(newsList: List<NewsEntity>) = newsDao.insertOrUpdate(newsList)

    override fun replaceAll(newsList: List<NewsEntity>) {
        appDatabase.runInTransaction {
            newsDao.deleteAndInsert(newsList)
        }
    }

    override fun find(id: Int): Single<NewsEntity> = newsDao.find(id)

    override fun findAll(): Single<List<NewsEntity>> = newsDao.findAll()
}