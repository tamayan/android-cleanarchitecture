package com.example.cleanarchitecture.di.component

import com.example.cleanarchitecture.di.module.ActivityModule
import com.example.cleanarchitecture.di.module.ApplicationModule
import com.example.cleanarchitecture.di.module.NewsListActivityModule
import com.example.cleanarchitecture.presentation.ui.activity.NewsListActivity
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
}