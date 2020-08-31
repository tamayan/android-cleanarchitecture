package com.example.cleanarchitecture.ui.news.list

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cleanarchitecture.ui.Event
import com.example.cleanarchitecture.usecase.news.list.GetNewsListRequest
import com.example.cleanarchitecture.usecase.news.list.GetNewsListUseCase
import com.example.cleanarchitecture.usecase.news.list.NewsListModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class NewsListViewModel @ViewModelInject constructor(
        private val getNewsListUseCase: GetNewsListUseCase
) : ViewModel() {

    private val _items = MutableLiveData<List<NewsListModel>>()
    val items: LiveData<List<NewsListModel>> = _items

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading

    // LiveDataでイベントを1度だけ実行したい場合に、Eventでラップすることで値を取り出したことがあるかを判定する
    private val _clickId = MutableLiveData<Event<Int>>()
    val clickId: LiveData<Event<Int>> = _clickId

    init {
        refresh()
    }

    fun refresh() {
        viewModelScope.launch {
            _loading.value = true
            getNewsListUseCase
                    .handle(GetNewsListRequest())
                    .newsListModels
                    .collect {
                        _items.value = it
                    }
            _loading.value = false
        }
    }

    fun onClickItem(id: Int) {
        _clickId.value = Event(id)
    }
}