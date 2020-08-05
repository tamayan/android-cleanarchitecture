package com.example.cleanarchitecture.presentation.mvp.presenter

import com.example.cleanarchitecture.domain.usecase.GetNewsListUseCase
import com.example.cleanarchitecture.presentation.entity.NewsViewEntity
import com.example.cleanarchitecture.presentation.mvp.view.NewsListView
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import javax.inject.Inject

/**
 * Created by tamayan on 2017/12/10.
 */

class NewsListPresenter @Inject constructor(
        private val getNewsListUseCase: GetNewsListUseCase,
        private val view: NewsListView
) : Presenter<NewsListView>(view) {

    private val compositeDisposable = CompositeDisposable()

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
                .addTo(compositeDisposable)
    }

    override fun stop() {
        // no-op
    }

    override fun destroy() {
        // no-op
        compositeDisposable.clear()
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
                .addTo(compositeDisposable)
    }
}