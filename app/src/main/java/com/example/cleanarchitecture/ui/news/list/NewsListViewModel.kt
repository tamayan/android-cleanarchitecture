package com.example.cleanarchitecture.ui.news.list

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cleanarchitecture.usecase.news.list.GetNewsListRequest
import com.example.cleanarchitecture.usecase.news.list.GetNewsListUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class NewsListViewModel(private val getNewsListUseCase: GetNewsListUseCase) : ViewModel() {

    val adapter = MutableLiveData<NewsListAdapter>()

    val isLoading = MediatorLiveData<Boolean>()

    init {
        adapter.value = NewsListAdapter()
    }

    fun load() = viewModelScope.launch {
        isLoading.value = true
        val newsModels = withContext(Dispatchers.IO) {
            getNewsListUseCase.handle(GetNewsListRequest()).newsListModels
        }
        adapter.value?.update(newsModels)
        isLoading.value = false
    }
}