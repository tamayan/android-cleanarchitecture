package com.tamayan.cleanarchitecture.presentation.mvp.view

import com.tamayan.cleanarchitecture.presentation.entity.NewsViewEntity
import io.reactivex.Observable

/**
 * Created by tamayan on 2017/12/10.
 */

interface NewsListView : ProgressView {

    fun showNewsList(list: List<NewsViewEntity>)

    fun showToast(message: String)

    fun newsListRefresh(): Observable<Unit>
}