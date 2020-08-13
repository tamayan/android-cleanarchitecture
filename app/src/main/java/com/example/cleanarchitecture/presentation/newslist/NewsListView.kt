package com.example.cleanarchitecture.presentation.newslist

import com.example.cleanarchitecture.presentation.entity.NewsViewEntity
import com.example.cleanarchitecture.presentation.ProgressView
import io.reactivex.Observable

/**
 * Created by tamayan on 2017/12/10.
 */

interface NewsListView : ProgressView {

    fun showNewsList(list: List<NewsViewEntity>)

    fun showToast(message: String)

    fun newsListRefresh(): Observable<Unit>
}