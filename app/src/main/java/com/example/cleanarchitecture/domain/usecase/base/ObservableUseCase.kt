package com.example.cleanarchitecture.domain.usecase.base

import io.reactivex.Observable
import io.reactivex.Scheduler

/**
 * Created by tamayan on 2017/12/10.
 ** UseCase のベースとなる抽象クラス
 * <p>
 * 実行時 ({@link #execute(Params)} 時) にサブクラスに {@link Observable} の生成を移譲し、生成された
 * Observable に subscribeOn を適用して返します。
 *
 * @param <T>      Observable が通知する型
 * @param <Params> Observable の生成に使用するパラメータ
 */

abstract class ObservableUseCase<T, in Params>(private val scheduler: Scheduler) {

    fun execute(params: Params): Observable<T> {
        return buildObservable(params)
                .subscribeOn(scheduler)
    }

    abstract fun buildObservable(params: Params): Observable<T>
}