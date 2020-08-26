package com.example.cleanarchitecture.domain.domain.news

import kotlinx.coroutines.flow.Flow

/**
 * Created by tamayan on 2017/12/09.
 */

interface NewsRepositoryInterface {

    fun find(id: Int): Flow<News>

    fun findAll(): Flow<List<News>>
}