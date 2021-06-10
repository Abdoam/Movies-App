package com.abdoam.domain.repository

import com.abdoam.domain.entities.MovieDetailsEntity
import com.abdoam.domain.entities.MovieResponseEntity
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single

interface MovieRepository {

    fun getMovieDetailsInfo(identifier: Int): Observable<MovieDetailsEntity>
    fun getPopularMovies(page: Int): Single<MovieResponseEntity>

}
