package com.tamayan.cleanarchitecture.di.component

import com.squareup.moshi.Moshi
import com.tamayan.cleanarchitecture.di.module.ApplicationModule
import com.tamayan.cleanarchitecture.data.datastore.cloud.client.okhttp.BasicCredentialProvider
import dagger.Component
import javax.inject.Singleton

/**
 * Created by MSnowRobin016 on 2017/12/28.
 */

@Singleton
@Component(modules = arrayOf(ApplicationModule::class))
interface ApplicationComponent {

    fun activityComponent(): ActivityComponent

    fun moshi(): Moshi

    fun basicCredentialProvider(): BasicCredentialProvider
}