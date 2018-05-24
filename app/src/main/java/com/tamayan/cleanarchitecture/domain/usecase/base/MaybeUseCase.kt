package com.tamayan.cleanarchitecture.domain.usecase.base

import io.reactivex.Maybe
import io.reactivex.Scheduler

/**
 * Created by tamayan on 2017/12/10.
 *
 *  UseCase のベースとなる抽象クラス
 * <p>
 * 実行時 ({@link #execute(Params)} 時) にサブクラスに {@link Maybe} の生成を移譲し、生成された
 * Maybe に subscribeOn を適用して返します。
 *
 * @param <T>      Maybe が onSuccess で通知する型
 * @param <Params> Maybe の生成に使用するパラメータ
 */

abstract class MaybeUseCase<T, Params>(private val scheduler: Scheduler) {

    fun execute(params: Params): Maybe<T> {
        return buildMaybe(params)
                .subscribeOn(scheduler)
    }

    abstract fun buildMaybe(params: Params): Maybe<T>
}