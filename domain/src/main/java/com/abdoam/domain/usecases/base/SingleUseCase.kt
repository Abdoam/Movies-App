package com.abdoam.domain.usecases.base

import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.core.Single

abstract class SingleUseCase<in Input, T> constructor(
    private val backgroundScheduler: Scheduler,
    private val foregroundScheduler: Scheduler
) {
    protected abstract fun generateSingle(input: Input? = null): Single<T>

    fun buildUseCase(input: Input? = null): Single<T> {
        return generateSingle(input)
            .subscribeOn(backgroundScheduler)
            .observeOn(foregroundScheduler)
    }

}