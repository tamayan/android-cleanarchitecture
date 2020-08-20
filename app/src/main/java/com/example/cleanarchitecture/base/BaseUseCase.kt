package com.example.cleanarchitecture.base

interface BaseUseCase<T, R> {

    fun handle(param: T): R
}