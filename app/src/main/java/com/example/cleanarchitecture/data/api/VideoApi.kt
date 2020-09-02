package com.example.cleanarchitecture.data.api

import retrofit2.http.GET

interface VideoApi {

    @GET("videos")
    suspend fun fetch(): List<VideoJson>
}