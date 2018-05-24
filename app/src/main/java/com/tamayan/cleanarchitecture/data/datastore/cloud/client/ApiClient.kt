package com.tamayan.cleanarchitecture.data.datastore.cloud.client

import io.reactivex.Single

/**
 * Created by tamayan on 2017/11/23.
 */

interface ApiClient<in Request, Response> {

    fun request(request: Request): Single<Response>
}