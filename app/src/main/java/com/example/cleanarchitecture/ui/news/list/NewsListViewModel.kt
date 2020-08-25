package com.example.cleanarchitecture.ui.news.list

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cleanarchitecture.usecase.news.list.GetNewsListRequest
import com.example.cleanarchitecture.usecase.news.list.GetNewsListUseCase
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class NewsListViewModel(private val getNewsListUseCase: GetNewsListUseCase) : ViewModel() {

    val adapter = MutableLiveData<NewsListAdapter>()

    val isLoading = MediatorLiveData<Boolean>()

//    val newsList: LiveData<List<NewsListModel>> = getNewsListUseCase
//            .handle(GetNewsListRequest())
//            .newsListModels
//            .asLiveData()

//    // エラーハンドリング用のハンドラーを用意
//    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
//        Timber.d(throwable)
//        isLoading.value = false
//    }

    init {
        adapter.value = NewsListAdapter()
    }

    fun load() {
        viewModelScope.launch {
            isLoading.value = true
            getNewsListUseCase
                    .handle(GetNewsListRequest())
                    .newsListModels
                    .collect {
                        adapter.value?.update(it)
                        isLoading.value = false
                    }
        }
    }
}