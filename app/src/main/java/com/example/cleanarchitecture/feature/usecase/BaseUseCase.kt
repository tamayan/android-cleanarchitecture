package com.example.cleanarchitecture.feature.usecase

interface BaseUseCase<T, R> {

    fun handle(param: T): R
}