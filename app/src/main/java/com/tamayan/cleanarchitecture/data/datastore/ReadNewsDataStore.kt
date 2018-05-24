package com.tamayan.cleanarchitecture.data.datastore

import com.tamayan.cleanarchitecture.domain.entity.News
import io.reactivex.Completable
import io.reactivex.Single

/**
 * Created by tamayan on 2017/11/23.
 */

interface ReadNewsDataStore {

    fun insertOrUpdate(newsList: List<News>): Completable

    fun fetch(id: Int): Single<News>

    fun fetch(): Single<List<News>>
}