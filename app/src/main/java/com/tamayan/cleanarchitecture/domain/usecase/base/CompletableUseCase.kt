package com.tamayan.cleanarchitecture.domain.usecase.base

import io.reactivex.Completable
import io.reactivex.Scheduler

/**
 * Created by tamayan on 2017/12/10.
 *
 *  UseCase のベースとなる抽象クラス
 * <p>
 * 実行時 ({@link #execute(Params)} 時) にサブクラスに {@link Completable} の生成を移譲し、生成された
 * Completable に subscribeOn を適用して返します。
 *
 * @param <Params> Completable の生成に使用するパラメータ
 */

abstract class CompletableUseCase<in Params>(private val scheduler: Scheduler) {

    fun execute(params: Params): Completable {
        return buildCompletable(params)
                .subscribeOn(scheduler)
    }

    abstract fun buildCompletable(params: Params): Completable
}