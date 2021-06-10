package com.abdoam.presentation.mapper

import com.abdoam.domain.entities.MovieEntity
import com.abdoam.domain.entities.MovieResponseEntity
import com.abdoam.presentation.model.Movie
import com.abdoam.presentation.model.MovieResponse
import javax.inject.Inject


class MovieResponsePresentDomainMapper @Inject constructor(val mapper: Mapper<Movie, MovieEntity>) :
    Mapper<MovieResponse, MovieResponseEntity> {
    override fun toDomain(movieResponse: MovieResponse): MovieResponseEntity {
        return MovieResponseEntity(
            page = movieResponse.page,
            movieList = movieResponse.movieList.asSequence().map {
                mapper.toDomain(it)
            }.toList(),
            totalPages = movieResponse.totalPages,
            totalResults = movieResponse.totalResults
        )
    }

    override fun toPresentation(movieResponseEntity: MovieResponseEntity): MovieResponse {
        return MovieResponse(
            page = movieResponseEntity.page,
            movieList = movieResponseEntity.movieList.asSequence().map {
                mapper.toPresentation(it)
            }.toList(),
            totalPages = movieResponseEntity.totalPages,
            totalResults = movieResponseEntity.totalResults
        )
    }

}