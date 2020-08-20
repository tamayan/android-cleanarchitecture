package com.example.cleanarchitecture.usecase.news.list

import com.example.cleanarchitecture.base.BaseUseCase
import io.reactivex.Single

interface GetNewsListUseCase : BaseUseCase<Unit, Single<GetNewsListResponse>>