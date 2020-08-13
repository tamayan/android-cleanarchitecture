package com.example.cleanarchitecture.di

import com.example.cleanarchitecture.presentation.newslist.NewsListActivity
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