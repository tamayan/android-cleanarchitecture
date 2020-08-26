package com.example.cleanarchitecture.ui.news.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.cleanarchitecture.usecase.news.list.GetNewsListRequest
import com.example.cleanarchitecture.usecase.news.list.GetNewsListUseCase
import com.example.cleanarchitecture.usecase.news.list.NewsListModel
import kotlinx.coroutines.flow.collect

class NewsListViewModel(private val getNewsListUseCase: GetNewsListUseCase) : ViewModel() {

    val items: LiveData<List<NewsListModel>> = liveData {
        getNewsListUseCase
                .handle(GetNewsListRequest())
                .newsListModels
                .collect {
                    emit(it)
                }
    }
}