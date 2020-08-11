package com.example.cleanarchitecture.di.component

import com.example.cleanarchitecture.data.datastore.cloud.client.okhttp.BasicCredentialProvider
import com.example.cleanarchitecture.data.datastore.disk.db.AppDatabase
import com.example.cleanarchitecture.di.module.ActivityModule
import com.example.cleanarchitecture.di.module.ApplicationModule
import com.example.cleanarchitecture.di.module.NewsListActivityModule
import com.example.cleanarchitecture.presentation.ui.activity.NewsListActivity
import com.squareup.moshi.Moshi
import dagger.Component
import javax.inject.Singleton

/**
 * Created by MSnowRobin016 on 2017/12/28.
 */

@Singleton
@Component(modules = [
    ApplicationModule::class,
    ActivityModule::class,
    NewsListActivityModule::class
])
interface ApplicationComponent {

    fun inject(newsListActivity: NewsListActivity)

    fun basicCredentialProvider(): BasicCredentialProvider

    fun moshi(): Moshi

    fun appDatabase(): AppDatabase

    //    fun activityComponent(): ActivityComponent
}