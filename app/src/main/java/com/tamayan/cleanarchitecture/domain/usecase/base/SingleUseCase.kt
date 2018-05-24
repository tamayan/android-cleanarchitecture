package com.tamayan.cleanarchitecture.domain.usecase.base

import io.reactivex.Scheduler
import io.reactivex.Single

/**
 * Created by tamayan on 2017/12/10.
 *
 * UseCase のベースとなる抽象クラス
 * <p>
 * 実行時 ({@link #execute(Params)} 時) にサブクラスに {@link Single} の生成を移譲し、生成された
 * Single に subscribeOn を適用して返します。
 *
 * @param <T>      Single が通知する型
 * @param <Params> Single の生成に使用するパラメータ
 */

abstract class SingleUseCase<T, in Params>(private val scheduler: Scheduler) {

    fun execute(params: Params): Single<T> {
        return buildSingle(params)
                .subscribeOn(scheduler)
    }

    abstract fun buildSingle(params: Params): Single<T>
}