package com.abdoam.domain.usecases

import com.abdoam.domain.entities.MovieResponseEntity
import com.abdoam.domain.qualifiers.Background
import com.abdoam.domain.qualifiers.Foreground
import com.abdoam.domain.repository.MovieRepository
import com.abdoam.domain.usecases.base.SingleUseCase
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class GetPopularMovies @Inject constructor(
    private val movieRepository: MovieRepository,
    @Background backgroundScheduler: Scheduler,
    @Foreground foregroundScheduler: Scheduler
) : SingleUseCase<Int, MovieResponseEntity>(backgroundScheduler, foregroundScheduler) {

    override fun generateSingle(input: Int?): Single<MovieResponseEntity> {
        if (input == null) {
            throw IllegalArgumentException("User identifier can't be null")
        }
        return movieRepository.getPopularMovies(input)
    }

}