package com.example.cleanarchitecture.di

import com.example.cleanarchitecture.presentation.newslist.NewsListActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by tamayan on 2018/02/02.
 */

@Module
abstract class ActivityModule {

    @ContributesAndroidInjector
    internal abstract fun contributeNewsListActivity(): NewsListActivity
}