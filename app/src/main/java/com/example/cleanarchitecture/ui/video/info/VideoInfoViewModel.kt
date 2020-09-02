package com.example.cleanarchitecture.ui.video.info

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.example.cleanarchitecture.usecase.video.info.GetVideoInfoRequest
import com.example.cleanarchitecture.usecase.video.info.GetVideoInfoResponse
import com.example.cleanarchitecture.usecase.video.info.GetVideoInfoUseCase

class VideoInfoViewModel @ViewModelInject constructor(
        private val getVideoInfoUseCase: GetVideoInfoUseCase
) : ViewModel() {

    private val _id = MutableLiveData<String>()

    val video: LiveData<GetVideoInfoResponse> = _id.switchMap {
        liveData {
            val request = GetVideoInfoRequest(it)
            emit(getVideoInfoUseCase.handle(request))
        }
    }

    fun find(id: String) {
        _id.value = id
    }
}