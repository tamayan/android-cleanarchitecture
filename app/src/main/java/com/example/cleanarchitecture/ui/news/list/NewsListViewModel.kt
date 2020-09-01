package com.example.cleanarchitecture.ui.news.list

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.example.cleanarchitecture.ui.Event
import com.example.cleanarchitecture.usecase.news.list.GetNewsListRequest
import com.example.cleanarchitecture.usecase.news.list.GetNewsListUseCase
import com.example.cleanarchitecture.usecase.news.list.NewsListModel
import kotlinx.coroutines.flow.collect

class NewsListViewModel @ViewModelInject constructor(
        private val getNewsListUseCase: GetNewsListUseCase
) : ViewModel() {

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading

    // LiveDataでイベントを1度だけ実行したい場合に、Eventでラップすることで値を取り出したことがあるかを判定する
    private val _clickId = MutableLiveData<Event<Int>>()
    val clickId: LiveData<Event<Int>> = _clickId

    private val _refresh = MutableLiveData(Unit)
    val items: LiveData<List<NewsListModel>> = _refresh.switchMap {
        liveData {
            _loading.value = true
            getNewsListUseCase
                    .handle(GetNewsListRequest())
                    .newsListModels
                    .collect {
                        emit(it)
                    }
            _loading.value = false
        }
    }

    fun refresh() {
        _refresh.value = Unit
    }

    fun onClickItem(id: Int) {
        _clickId.value = Event(id)
    }
}