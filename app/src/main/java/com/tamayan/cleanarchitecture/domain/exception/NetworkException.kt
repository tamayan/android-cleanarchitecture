package com.tamayan.cleanarchitecture.domain.exception

import java.lang.RuntimeException

/**
 * Created by tamayan on 2017/11/23.
 * <p>
 * 何らかの原因でネットワークアクセス中に問題が発生したことを表します。
 * <p>
 * 例えば、通信中に端末が圏外になった場合や、通信がタイムアウトした場合です。
 */

class NetworkException(message: String, cause: Throwable) : RuntimeException(message, cause)