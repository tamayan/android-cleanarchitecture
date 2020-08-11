package com.example.cleanarchitecture.di.module

import com.example.cleanarchitecture.data.datastore.cloud.client.okhttp.BasicCredentialProvider
import com.example.cleanarchitecture.data.datastore.disk.db.AppDatabase
import com.example.cleanarchitecture.data.repository.BasicCredentialProviderImpl
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by MSnowRobin016 on 2017/12/28.
 */

@Module
class ApplicationModule(private val appDatabase: AppDatabase) {

    @Singleton
    @Provides
    fun provideMoshi(): Moshi = Moshi
            .Builder()
            .add(KotlinJsonAdapterFactory())
            .build()

    @Singleton
    @Provides
    fun provideBasicCredentialProvider(): BasicCredentialProvider =
            BasicCredentialProviderImpl()

    @Singleton
    @Provides
    fun provideRoom(): AppDatabase = appDatabase
}