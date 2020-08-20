package com.example.cleanarchitecture.feature.domain.application.news

import com.example.cleanarchitecture.feature.domain.domain.news.NewsRepositoryInterface
import com.example.cleanarchitecture.feature.usecase.news.list.GetNewsListResponse
import com.example.cleanarchitecture.feature.usecase.news.list.GetNewsListUseCase
import com.example.cleanarchitecture.feature.usecase.news.list.NewsListModel
import io.reactivex.Observable
import io.reactivex.Single

/**
 * Created by tamayan on 2017/12/10.
 */

class GetNewsListInteractor(private val repository: NewsRepositoryInterface) : GetNewsListUseCase {

    override fun handle(param: Unit): Single<GetNewsListResponse> =
            repository
                    .findAll()
                    .flatMapObservable { Observable.fromIterable(it) }
                    .map { NewsListModel(it.id, it.title) }
                    .toList()
                    .map { GetNewsListResponse(it) }
}