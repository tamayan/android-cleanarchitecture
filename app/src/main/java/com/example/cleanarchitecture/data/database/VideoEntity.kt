package com.example.cleanarchitecture.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "video")
data class VideoEntity(
        @PrimaryKey
        val id: String,
        val title: String,
        val text: String
)