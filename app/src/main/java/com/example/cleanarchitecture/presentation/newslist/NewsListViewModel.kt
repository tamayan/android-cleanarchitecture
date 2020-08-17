package com.example.cleanarchitecture.presentation.newslist

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cleanarchitecture.domain.usecase.GetNewsListUseCase
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
                .execute(Unit)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .doOnSubscribe { isLoading.value = true }
                .doFinally { isLoading.value = false }
                .subscribeBy(
                        onSuccess = { newsList ->
                            adapter.value?.update(
                                    newsList.map {
                                        NewsViewEntity(it.id, it.text)
                                    }
                            )
                        },
                        onError = {
                            it.printStackTrace()
                        }
                )
                .addTo(compositeDisposable)
    }
}