package com.example.cleanarchitecture.feature.infrastructure.remote.retrofit

/**
 * Created by tamayan on 2017/11/23.
 *
 * お知らせのJsonEntityです。
 */

data class NewsJson(
        val id: Int,
        val title: String,
        val text: String
)