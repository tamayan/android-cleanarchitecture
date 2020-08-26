package com.example.cleanarchitecture.ui.news.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.liveData
import com.example.cleanarchitecture.usecase.news.list.GetNewsListRequest
import com.example.cleanarchitecture.usecase.news.list.GetNewsListUseCase
import com.example.cleanarchitecture.usecase.news.list.NewsListModel

class NewsListViewModel(private val getNewsListUseCase: GetNewsListUseCase) : ViewModel() {

    val items: LiveData<List<NewsListModel>> = liveData {
        emitSource(getNewsListUseCase.handle(GetNewsListRequest()).newsListModels.asLiveData())
    }
}