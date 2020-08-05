package com.example.cleanarchitecture.domain.exception

/**
 * Created by tamayan on 2017/11/23.
 * <p>
 * API 等の外部との通信で得られたデータのフォーマットが期待したフォーマットではないことを表します。
 * <p>
 * 例えば、必須項目として定義されている値が存在しない場合や、
 * NonNull で定義されているフィールドに null 値が現れた場合です。
 */

class WrongFormatException : RuntimeException {

    constructor(message: String) : super(message)

    constructor(message: String, cause: Throwable) : super(message, cause)
}