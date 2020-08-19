package com.example.cleanarchitecture.feature.domain.domain.news

/**
 * Created by tamayan on 2017/11/23.
 *
 * お知らせのDomainEntityです。
 */

data class News(
        val id: Int,
        val title: String,
        val text: String
)