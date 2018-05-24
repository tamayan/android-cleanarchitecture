package com.tamayan.cleanarchitecture.di.component

import com.tamayan.cleanarchitecture.di.module.NewsListActivityModule
import com.tamayan.cleanarchitecture.presentation.ui.activity.NewsListActivity
import dagger.Subcomponent

/**
 * Created by tamayan on 2018/02/04.
 */

@Subcomponent(modules = arrayOf(NewsListActivityModule::class))
interface NewsListActivityComponent {

    fun inject(newsListActivity: NewsListActivity)
}