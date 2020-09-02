package com.example.cleanarchitecture.ui.video.list

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.example.cleanarchitecture.ui.Event
import com.example.cleanarchitecture.usecase.video.list.GetVideosRequest
import com.example.cleanarchitecture.usecase.video.list.GetVideosUseCase
import com.example.cleanarchitecture.usecase.video.list.VideoModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart

class VideosViewModel @ViewModelInject constructor(
        private val getVideosUseCase: GetVideosUseCase
) : ViewModel() {

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading

    // LiveDataでイベントを1度だけ実行したい場合に、Eventでラップすることで値を取り出したことがあるかを判定する
    private val _clickId = MutableLiveData<Event<String>>()
    val clickId: LiveData<Event<String>> = _clickId

    private val _refresh = MutableLiveData(Unit)
    val items: LiveData<List<VideoModel>> = _refresh.switchMap {
        getVideosUseCase
                .handle(GetVideosRequest())
                .videos
                .flowOn(Dispatchers.IO) // 上部のスレッドを切り替え
                .onStart { _loading.value = true }
                .onCompletion { _loading.value = false }
                .asLiveData()
    }

    fun refresh() {
        _refresh.value = Unit
    }

    fun onClickItem(id: String) {
        _clickId.value = Event(id)
    }
}