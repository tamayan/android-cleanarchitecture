package com.example.cleanarchitecture.domain.application.news

import com.example.cleanarchitecture.domain.domain.news.NewsRepositoryInterface
import com.example.cleanarchitecture.usecase.news.list.GetNewsListRequest
import com.example.cleanarchitecture.usecase.news.list.GetNewsListResponse
import com.example.cleanarchitecture.usecase.news.list.GetNewsListUseCase
import com.example.cleanarchitecture.usecase.news.list.NewsListModel
import io.reactivex.Observable
import io.reactivex.Single

/**
 * Created by tamayan on 2017/12/10.
 */

class GetNewsListInteractor(private val repository: NewsRepositoryInterface) : GetNewsListUseCase {

    override fun handle(request: GetNewsListRequest): Single<GetNewsListResponse> =
            repository
                    .findAll()
                    .flatMapObservable { Observable.fromIterable(it) }
                    .map { NewsListModel(it.id, it.title) }
                    .toList()
                    .map { GetNewsListResponse(it) }
}