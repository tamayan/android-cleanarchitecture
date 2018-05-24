package com.tamayan.cleanarchitecture.di.module

import com.squareup.moshi.KotlinJsonAdapterFactory
import com.squareup.moshi.Moshi
import com.tamayan.cleanarchitecture.data.datastore.cloud.client.okhttp.BasicCredentialProvider
import com.tamayan.cleanarchitecture.data.repository.BasicCredentialProviderImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by MSnowRobin016 on 2017/12/28.
 */

@Module
class ApplicationModule {

    @Provides
    @Singleton
    fun provideMoshi(): Moshi {
        return Moshi.Builder()
                .add(KotlinJsonAdapterFactory())
                .build()
    }

    @Provides
    @Singleton
    fun provideBasicCredentialProvider(): BasicCredentialProvider {
        return BasicCredentialProviderImpl()
    }
}