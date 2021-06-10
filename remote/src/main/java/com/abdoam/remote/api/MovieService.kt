package com.abdoam.remote.api

import com.abdoam.remote.model.MovieDetailsNetwork
import com.abdoam.remote.model.MovieResponseNetwork
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieService {
    @GET("movie/{movie_id}")
    fun getMovieDetails(@Path("movie_id") id: Int): Observable<MovieDetailsNetwork>

    @GET("movie/popular")
    fun getPopularMovies(@Query("page") page: Int): Single<MovieResponseNetwork>
}