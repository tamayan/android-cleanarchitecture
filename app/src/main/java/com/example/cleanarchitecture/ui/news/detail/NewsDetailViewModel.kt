package com.example.cleanarchitecture.ui.news.detail

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.example.cleanarchitecture.usecase.news.detail.GetNewsDetailRequest
import com.example.cleanarchitecture.usecase.news.detail.GetNewsDetailResponse
import com.example.cleanarchitecture.usecase.news.detail.GetNewsDetailUseCase

class NewsDetailViewModel @ViewModelInject constructor(
        private val getNewsDetailUseCase: GetNewsDetailUseCase
) : ViewModel() {

    private val _id = MutableLiveData<Int>()

    val newsDetail: LiveData<GetNewsDetailResponse> = _id.switchMap {
        liveData {
            val request = GetNewsDetailRequest(it)
            emit(getNewsDetailUseCase.handle(request))
        }
    }

    fun find(id: Int) {
        _id.value = id
    }
}