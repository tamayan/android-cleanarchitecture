package com.tamayan.cleanarchitecture.data.entity.json

import com.squareup.moshi.Json

/**
 * Created by tamayan on 2017/12/05.
 */

data class NewsResponseListEntity(
        @Json(name = "news_list") val list: List<NewsResponseEntity>)