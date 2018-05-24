package com.tamayan.cleanarchitecture.di.component

import com.tamayan.cleanarchitecture.di.module.ActivityModule
import com.tamayan.cleanarchitecture.di.module.NewsListActivityModule
import dagger.Subcomponent

/**
 * Created by tamayan on 2018/02/02.
 */

@Subcomponent(modules = arrayOf(ActivityModule::class))
interface ActivityComponent {

    fun newsListActivityComponent(newsListActivityModule: NewsListActivityModule): NewsListActivityComponent
}