package com.example.cleanarchitecture.feature.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "news")
data class NewsEntity(

        @PrimaryKey
        val id: Int,

        val title: String,

        val text: String
)