package com.abdoam.data.repository

import com.abdoam.data.model.MovieDetailsData
import com.abdoam.data.model.MovieResponseData
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single

interface RemoteDataSource {
    fun getMovieDetails(id: Int): Observable<MovieDetailsData>
    fun getPopularMovies(page: Int): Single<MovieResponseData>
}