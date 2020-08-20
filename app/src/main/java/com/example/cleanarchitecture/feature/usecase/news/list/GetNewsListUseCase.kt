package com.example.cleanarchitecture.feature.usecase.news.list

import com.example.cleanarchitecture.feature.usecase.BaseUseCase
import io.reactivex.Single

interface GetNewsListUseCase : BaseUseCase<Unit, Single<GetNewsListResponse>>