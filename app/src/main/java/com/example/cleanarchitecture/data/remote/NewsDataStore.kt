package com.example.cleanarchitecture.data.remote

import com.example.cleanarchitecture.domain.entity.News
import io.reactivex.Single

/**
 * Created by tamayan on 2017/11/23.
 */

interface NewsDataStore {

    fun getNewsList(): Single<List<News>>
}