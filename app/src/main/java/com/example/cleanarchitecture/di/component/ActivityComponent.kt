package com.example.cleanarchitecture.di.component

import com.example.cleanarchitecture.di.module.ActivityModule
import com.example.cleanarchitecture.di.module.NewsListActivityModule
import dagger.Subcomponent

/**
 * Created by tamayan on 2018/02/02.
 */

@Subcomponent(modules = [ActivityModule::class])
interface ActivityComponent {

    fun newsListActivityComponent(newsListActivityModule: NewsListActivityModule): NewsListActivityComponent
}