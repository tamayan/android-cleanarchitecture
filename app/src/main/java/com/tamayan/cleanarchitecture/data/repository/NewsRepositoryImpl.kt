package com.tamayan.cleanarchitecture.data.repository

import com.tamayan.cleanarchitecture.data.datastore.NewsDataStore
import com.tamayan.cleanarchitecture.data.datastore.ReadNewsDataStore
import com.tamayan.cleanarchitecture.domain.entity.News
import com.tamayan.cleanarchitecture.domain.repository.NewsRepository
import io.reactivex.Completable
import io.reactivex.Single

/**
 * Created by tamayan on 2017/12/09.
 */

class NewsRepositoryImpl(private val newsDataStore: NewsDataStore,
                         private val readNewsDataStore: ReadNewsDataStore) : NewsRepository {

    override fun getNewsList(): Single<List<News>> = newsDataStore.getNewsList()

    override fun insertOrUpdate(newsList: List<News>): Completable = readNewsDataStore.insertOrUpdate(newsList)

    override fun fetch(id: Int): Single<News> = readNewsDataStore.fetch(id)

    override fun fetch(): Single<List<News>> = readNewsDataStore.fetch()
}