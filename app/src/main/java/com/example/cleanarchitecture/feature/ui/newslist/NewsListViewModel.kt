package com.example.cleanarchitecture.feature.ui.newslist

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cleanarchitecture.feature.usecase.news.list.GetNewsListUseCase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

class NewsListViewModel(private val getNewsListUseCase: GetNewsListUseCase) : ViewModel() {

    val adapter = MutableLiveData<NewsListAdapter>()

    val isLoading = MediatorLiveData<Boolean>()

    private val compositeDisposable = CompositeDisposable()

    init {
        adapter.value = NewsListAdapter()
    }

    fun load() {
        getNewsListUseCase
                .handle(Unit)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .doOnSubscribe { isLoading.value = true }
                .doFinally { isLoading.value = false }
                .subscribeBy(
                        onSuccess = {
                            adapter.value?.update(it.newsListModel)
                        },
                        onError = {
                            it.printStackTrace()
                        }
                )
                .addTo(compositeDisposable)
    }
}