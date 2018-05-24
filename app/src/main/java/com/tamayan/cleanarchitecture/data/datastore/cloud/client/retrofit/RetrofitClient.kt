package com.tamayan.cleanarchitecture.data.datastore.cloud.client.retrofit

import com.tamayan.cleanarchitecture.data.datastore.cloud.client.ApiClient
import com.tamayan.cleanarchitecture.data.datastore.cloud.client.okhttp.BasicAuthenticationInterceptor
import com.tamayan.cleanarchitecture.data.datastore.cloud.client.okhttp.BasicCredentialProvider
import com.tamayan.cleanarchitecture.domain.exception.NetworkException
import io.reactivex.Single
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import java.net.SocketTimeoutException
import java.net.UnknownHostException

/**
 * Created by tamayan on 2017/11/23.
 */

abstract class RetrofitClient<in RequestEntity, ResponseEntity>(private val baseUrl: String,
                                                                private val basicCredentialProvider: BasicCredentialProvider) : ApiClient<RequestEntity, ResponseEntity> {

    override fun request(request: RequestEntity): Single<ResponseEntity> {
        return Single.create { emitter ->
            val retrofit = buildRetrofit(builder())

            try {
                // 実際のリクエスト処理をサブクラスに移譲
                val response = requestActual(retrofit, request)

                // レスポンスからデータを抽出
                // 抽出できなければ例外発生
                val extractor = ResponseExtractor<ResponseEntity>()
                val entity = extractor.extractOrThrow(response)

                // 抽出したデータを通知
                if (!emitter.isDisposed) {
                    emitter.onSuccess(entity)
                }
            }
            catch (exception: UnknownHostException) {
                if (!emitter.isDisposed) {
                    emitter.onError(NetworkException("Unknown host.", exception))
                }
            }
            catch (exception: SocketTimeoutException) {
                if (!emitter.isDisposed) {
                    emitter.onError(NetworkException("Socket timed out.", exception))
                }
            }
            catch (exception: RuntimeException) {
                if (!emitter.isDisposed) {
                    emitter.onError(exception)
                }
            }
        }
    }

    private fun builder(): Retrofit.Builder {
        val client = OkHttpClient
                .Builder()
                .addInterceptor(BasicAuthenticationInterceptor(basicCredentialProvider))
                .build()

        return Retrofit
                .Builder()
                .baseUrl(baseUrl)
                .client(client)
    }

    /**
     * 指定された [Retrofit.Builder] で Retrofit をビルドして返します。
     *
     * @param builder Retrofit のビルドに用いる Builder
     *
     * @return Builder でビルドした Retrofit のインスタンス
     */
    protected abstract fun buildRetrofit(builder: Retrofit.Builder): Retrofit

    /**
     * 実際のリクエスト処理を行い、取得した [ResponseEntity] を返します。
     *
     * @param retrofit      リクエストに使用する Retrofit インスタンス
     * @param requestEntity リクエストに使用する [RequestEntity] のインスタンス
     *
     * @return リクエストで取得したレスポンス
     *
     * @throws Exception リクエスト時に発生した例外（requestActual の呼び出し元で catch します）
     */
    @Throws(Exception::class)
    protected abstract fun requestActual(retrofit: Retrofit, requestEntity: RequestEntity): Response<ResponseEntity>
}