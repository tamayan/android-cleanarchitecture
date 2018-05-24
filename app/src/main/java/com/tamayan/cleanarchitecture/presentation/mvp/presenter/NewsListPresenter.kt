package com.tamayan.cleanarchitecture.presentation.mvp.presenter

import com.tamayan.cleanarchitecture.domain.usecase.GetNewsListUseCase
import com.tamayan.cleanarchitecture.presentation.entity.NewsViewEntity
import com.tamayan.cleanarchitecture.presentation.mvp.view.NewsListView
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import javax.inject.Inject

/**
 * Created by tamayan on 2017/12/10.
 */

class NewsListPresenter @Inject constructor(
        private val getNewsListUseCase: GetNewsListUseCase,
        private val view: NewsListView
) : Presenter<NewsListView>(view) {

    override fun start() {
        getNewsList()
        view.newsListRefresh()
                .subscribeBy(
                        onNext = {
                            getNewsList()
                        },
                        onError = {
                            view.showToast(it.message.toString())
                        }
                )
    }

    override fun stop() {
        // no-op
    }

    override fun destroy() {
        // no-op
    }

    private fun getNewsList() {
        getNewsListUseCase
                .execute(Unit)
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { view.showProgress() }
                .doFinally { view.hideProgress() }
                .flatMapObservable { Observable.fromIterable(it) }
                .map { NewsViewEntity(id = it.id, title = it.title) }
                .toList()
                .subscribeBy(
                        onSuccess = {
                            view.showNewsList(it)
                        },
                        onError = {
                            view.showToast(it.message.toString())
                        }
                )
    }
}