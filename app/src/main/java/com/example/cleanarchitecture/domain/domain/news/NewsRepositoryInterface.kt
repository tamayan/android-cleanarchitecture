package com.example.cleanarchitecture.domain.domain.news

import kotlinx.coroutines.flow.Flow

/**
 * Created by tamayan on 2017/12/09.
 */

interface NewsRepositoryInterface {

    suspend fun find(id: Int): News

    fun findAll(): Flow<List<News>>
}