package com.example.cleanarchitecture.ui.news.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.cleanarchitecture.usecase.news.list.GetNewsListRequest
import com.example.cleanarchitecture.usecase.news.list.GetNewsListUseCase
import com.example.cleanarchitecture.usecase.news.list.NewsListModel

class NewsListViewModel(getNewsListUseCase: GetNewsListUseCase) : ViewModel() {

    val items: LiveData<List<NewsListModel>> =
            getNewsListUseCase
                    .handle(GetNewsListRequest())
                    .newsListModels
                    .asLiveData()
}