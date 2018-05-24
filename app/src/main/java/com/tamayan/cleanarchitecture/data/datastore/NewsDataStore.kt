package com.tamayan.cleanarchitecture.data.datastore

import com.tamayan.cleanarchitecture.domain.entity.News
import io.reactivex.Single

/**
 * Created by tamayan on 2017/11/23.
 */

interface NewsDataStore {

    fun getNewsList(): Single<List<News>>
}