package com.tamayan.cleanarchitecture.domain.usecase

import com.tamayan.cleanarchitecture.domain.entity.News
import com.tamayan.cleanarchitecture.domain.repository.NewsRepository
import com.tamayan.cleanarchitecture.domain.usecase.base.SingleUseCase
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

/**
 * Created by tamayan on 2017/12/10.
 */

class GetNewsListUseCase(private val repository: NewsRepository) :
        SingleUseCase<List<News>, Unit>(Schedulers.io()) {

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