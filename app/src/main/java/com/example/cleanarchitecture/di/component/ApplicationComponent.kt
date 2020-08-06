package com.example.cleanarchitecture.di.component

import com.example.cleanarchitecture.data.datastore.cloud.client.okhttp.BasicCredentialProvider
import com.example.cleanarchitecture.di.module.ApplicationModule
import com.squareup.moshi.Moshi
import dagger.Component
import javax.inject.Singleton

/**
 * Created by MSnowRobin016 on 2017/12/28.
 */

@Singleton
@Component(modules = [ApplicationModule::class])
interface ApplicationComponent {

    fun activityComponent(): ActivityComponent

    fun moshi(): Moshi

    fun basicCredentialProvider(): BasicCredentialProvider
}