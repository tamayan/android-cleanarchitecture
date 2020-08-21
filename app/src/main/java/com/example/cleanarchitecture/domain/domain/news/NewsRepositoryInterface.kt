package com.example.cleanarchitecture.domain.domain.news

/**
 * Created by tamayan on 2017/12/09.
 */

interface NewsRepositoryInterface {

    suspend fun save(newsList: List<News>)

    suspend fun find(id: Int): News

    suspend fun findAll(): List<News>
}