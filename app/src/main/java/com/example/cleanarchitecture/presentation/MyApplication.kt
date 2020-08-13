package com.example.cleanarchitecture.presentation

import android.app.Activity
import android.app.Application
import com.example.cleanarchitecture.di.ApplicationComponent
import com.example.cleanarchitecture.di.ApplicationModule
import com.example.cleanarchitecture.di.DaggerApplicationComponent
import com.example.cleanarchitecture.di.NewsListActivityModule
import timber.log.Timber
import timber.log.Timber.DebugTree

/**
 * Created by MSnowRobin016 on 2017/12/28.
 */

class MyApplication : Application() {

    val applicationComponent: ApplicationComponent by lazy {
        DaggerApplicationComponent
                .builder()
                .applicationModule(ApplicationModule(this))
                .newsListActivityModule(NewsListActivityModule())
                .build()
    }

    override fun onCreate() {
        super.onCreate()
        Timber.plant(DebugTree())
    }
}

val Activity.myApplication get() = application as MyApplication