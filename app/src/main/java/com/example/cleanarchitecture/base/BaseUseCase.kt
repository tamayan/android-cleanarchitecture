package com.example.cleanarchitecture.base

interface BaseUseCase<T, R> {

    fun handle(request: T): R
}