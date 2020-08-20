package com.example.cleanarchitecture.ui

import com.example.cleanarchitecture.di.DaggerApplicationComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import timber.log.Timber
import timber.log.Timber.DebugTree

/**
 * Created by MSnowRobin016 on 2017/12/28.
 */

class MyApplication : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerApplicationComponent
                .factory()
                .create(this)
    }

    override fun onCreate() {
        super.onCreate()
        Timber.plant(DebugTree())
    }
}