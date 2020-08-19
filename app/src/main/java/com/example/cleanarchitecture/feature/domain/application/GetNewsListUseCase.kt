package com.example.cleanarchitecture.feature.domain.application

import com.example.cleanarchitecture.core.usecase.SingleUseCase
import com.example.cleanarchitecture.feature.domain.domain.news.News
import com.example.cleanarchitecture.feature.domain.domain.news.NewsRepository
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

/**
 * Created by tamayan on 2017/12/10.
 */

class GetNewsListUseCase(private val repository: NewsRepository) : SingleUseCase<List<News>, Unit>(Schedulers.io()) {

    override fun buildSingle(params: Unit): Single<List<News>> {
        return repository
                // APIからNewsListを取得
                .getNewsList()
                // 保存or更新する
                .flatMapCompletable {
                    repository.insertOrUpdate(it)
                }
                // Realmから取り出す
                .andThen(repository.fetch())
                // エラーの場合は、Realmから取り出す
                .onErrorResumeNext {
                    repository.fetch()
                }
    }
}