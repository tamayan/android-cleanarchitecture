package com.example.cleanarchitecture.data.datastore.remote

/**
 * Created by tamayan on 2017/11/23.
 *
 * お知らせのJsonEntityです。
 */

data class NewsResponseEntity(
        val id: Int,
        val title: String,
        val text: String
)