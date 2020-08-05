package com.example.cleanarchitecture.data.datastore.cloud.client.retrofit

import com.example.cleanarchitecture.domain.exception.ResponseIsNot200Exception
import com.example.cleanarchitecture.domain.exception.WrongFormatException
import retrofit2.Response

/**
 * Created by tamayan on 2017/11/23.
 */

class ResponseExtractor<T> {

    /**
     * 指定した [Response] からレスポンスデータを抽出して返します。
     *
     *
     * Response のステータスコードが 200 ではなかった場合や、レスポンスデータが null だった場合は
     * 例外を発生させます。
     *
     * @param response レスポンスデータを抽出する Response
     *
     * @throws ResponseIsNot200Exception ステータスコード ([Response.code]) が 200 ではない場合
     * @throws WrongFormatException      取得したデータ ([Response.body]) が null の場合
     */
    @Throws(ResponseIsNot200Exception::class, WrongFormatException::class)
    fun extractOrThrow(response: Response<T>): T {
        val statusCode = response.code()
        if (statusCode != 200) {
            throw ResponseIsNot200Exception("The response status code is not 200 ($statusCode).", statusCode)
        }

        return response.body() ?: throw WrongFormatException("The response body is null.")
    }
}