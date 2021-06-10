package com.abdoam.domain.usecases

import com.abdoam.domain.entities.MovieDetailsEntity
import com.abdoam.domain.qualifiers.Background
import com.abdoam.domain.qualifiers.Foreground
import com.abdoam.domain.repository.MovieRepository
import com.abdoam.domain.usecases.base.ObservableUseCase
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Scheduler
import javax.inject.Inject

class GetMovieDetailsInfo @Inject constructor(
    private val movieRepository: MovieRepository,
    @Background backgroundScheduler: Scheduler,
    @Foreground foregroundScheduler: Scheduler
) : ObservableUseCase<Int, MovieDetailsEntity>(backgroundScheduler, foregroundScheduler) {

    override fun generateObservable(input: Int?): Observable<MovieDetailsEntity> {
        if (input == null) {
            throw IllegalArgumentException("User identifier can't be null")
        }
        return movieRepository.getMovieDetailsInfo(input)
    }

}