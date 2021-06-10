package com.abdoam.remote.source

import com.abdoam.data.model.MovieDetailsData
import com.abdoam.data.model.MovieResponseData
import com.abdoam.data.repository.RemoteDataSource
import com.abdoam.remote.api.MovieService
import com.abdoam.remote.mapper.Mapper
import com.abdoam.remote.model.MovieDetailsNetwork
import com.abdoam.remote.model.MovieResponseNetwork
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(
    private val movieDetailsMapper: Mapper<MovieDetailsNetwork, MovieDetailsData>,
    private val movieMapper: Mapper<MovieResponseNetwork, MovieResponseData>,
    private val movieService: MovieService
) : RemoteDataSource {
    override fun getMovieDetails(id: Int): Observable<MovieDetailsData> {
        return movieService.getMovieDetails(id)
            .map {
                movieDetailsMapper.toData(it)
            }
    }

    override fun getPopularMovies(page: Int): Single<MovieResponseData> {
        return movieService.getPopularMovies(page)
            .map {
                movieMapper.toData(it)
            }
    }

}