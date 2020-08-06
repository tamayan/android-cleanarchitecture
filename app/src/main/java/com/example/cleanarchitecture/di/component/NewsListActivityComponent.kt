package com.example.cleanarchitecture.di.component

import com.example.cleanarchitecture.di.module.NewsListActivityModule
import com.example.cleanarchitecture.presentation.ui.activity.NewsListActivity
import dagger.Subcomponent

/**
 * Created by tamayan on 2018/02/04.
 */

@Subcomponent(modules = [NewsListActivityModule::class])
interface NewsListActivityComponent {

    fun inject(newsListActivity: NewsListActivity)
}