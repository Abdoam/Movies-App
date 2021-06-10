package com.abdoam.data.repository

import com.abdoam.data.mapper.Mapper
import com.abdoam.data.model.MovieDetailsData
import com.abdoam.data.model.MovieResponseData
import com.abdoam.domain.entities.MovieDetailsEntity
import com.abdoam.domain.entities.MovieResponseEntity
import com.abdoam.domain.repository.MovieRepository
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val movieDetailsMapper: Mapper<MovieDetailsData, MovieDetailsEntity>,
    private val movieResponseMapper: Mapper<MovieResponseData, MovieResponseEntity>,
    private val remoteDataSource: RemoteDataSource
) : MovieRepository {
    override fun getMovieDetailsInfo(identifier: Int): Observable<MovieDetailsEntity> {
        return remoteDataSource.getMovieDetails(identifier)
            .map {
                movieDetailsMapper.toDomain(it)
            }
    }

    override fun getPopularMovies(page: Int): Single<MovieResponseEntity> {
        return remoteDataSource.getPopularMovies(page)
            .map {
                movieResponseMapper.toDomain(it)
            }
    }
}