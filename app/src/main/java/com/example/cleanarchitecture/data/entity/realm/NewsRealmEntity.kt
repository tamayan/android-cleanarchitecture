package com.example.cleanarchitecture.data.entity.realm

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

/**
 * Created by tamayan on 2017/11/23.
 *
 * お知らせのRealmEntityです。
 */

open class NewsRealmEntity(

        @PrimaryKey
        open var id: Int? = null,

        var title: String? = null

) : RealmObject()