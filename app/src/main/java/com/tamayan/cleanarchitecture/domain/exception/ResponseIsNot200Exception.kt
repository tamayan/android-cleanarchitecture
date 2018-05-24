package com.tamayan.cleanarchitecture.domain.exception

/**
 * Created by tamayan on 2017/11/23.
 *
 * HTTP通信のResponseCodeが 200 以外で返ってきたことを表します。
 */

class ResponseIsNot200Exception(message: String, private val statusCode: Int) :
        RuntimeException(message)