package com.tamayan.cleanarchitecture.presentation.mvp.presenter

/**
 * Created by tamayan on 2017/12/10.
 */

abstract class Presenter<in View>(private val view: View) {

    /**
     * この Presenter を開始します。
     * <p>
     * ここではデータの監視を開始したり、ロジックを開始したりします。
     * ここで開始する作業は {@link #stop()} で停止できる必要があります。
     */
    abstract fun start()

    /**
     * この Presenter を停止します。
     * <p>
     * ここではデータの監視を終了したり、ロジックを停止したりします。
     * ここで終了する作業は {@link #start()} で再び開始できる必要があります。
     * ただし、必ずしも中断した時点から処理を再開できる必要はありません。
     */
    abstract fun stop()

    /**
     * この Presenter を一時停止します。
     * <p>
     * ここではデータの監視を一時停止したり、ロジックを一時停止したりします。
     * ここで一時停止する作業は {@link #resume()} で一時停止した時点から再開できる必要があります。
     * <p>
     * このメソッドは必須ではないため、一時停止/再開をサポートする場合のみオーバーライドします。
     */
    fun pause() {
    }

    /**
     * この Presenter を一時停止から再開します。
     * <p>
     * ここでは一時停止しているデータの監視やロジックを、一時停止した時点から再開します。
     * ここで再開する作業は再び {@link #pause()} で一時停止できる必要があります。
     * <p>
     * このメソッドは必須ではないため、一時停止/再開をサポートする場合のみオーバーライドします。
     */
    fun resume() {
    }

    /**
     * この Presenter を破棄します。
     * <p>
     * ここではリソースの開放や、処理のファイナライズを行います。
     * このメソッドが呼び出された後にこの Presenter インスタンスは破棄され、
     * 再び使用されることはありません。
     */
    abstract fun destroy()
}