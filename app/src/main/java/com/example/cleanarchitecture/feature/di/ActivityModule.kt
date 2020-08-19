package com.example.cleanarchitecture.feature.di

import com.example.cleanarchitecture.feature.di.news.NewsModule
import com.example.cleanarchitecture.feature.di.news.NewsScope
import com.example.cleanarchitecture.feature.ui.newslist.NewsListActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by tamayan on 2018/02/02.
 */

@Module
abstract class ActivityModule {

    @NewsScope
    @ContributesAndroidInjector(modules = [NewsModule::class])
    abstract fun contributeNewsListActivity(): NewsListActivity
}