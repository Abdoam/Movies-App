package com.abdoam.data.mapper

import com.abdoam.data.model.MovieData
import com.abdoam.data.model.MovieResponseData
import com.abdoam.domain.entities.MovieEntity
import com.abdoam.domain.entities.MovieResponseEntity
import javax.inject.Inject


class MovieResponseDataDomainMapper @Inject constructor(val mapper: Mapper<MovieData, MovieEntity>) :
    Mapper<MovieResponseData, MovieResponseEntity> {
    override fun toDomain(movieResponseData: MovieResponseData): MovieResponseEntity {
        return MovieResponseEntity(
            page = movieResponseData.page,
            movieList = movieResponseData.movieList.asSequence().map {
                mapper.toDomain(it)
            }.toList(),
            totalPages = movieResponseData.totalPages,
            totalResults = movieResponseData.totalResults
        )
    }

    override fun toData(movieResponseEntity: MovieResponseEntity): MovieResponseData {
        return MovieResponseData(
            page = movieResponseEntity.page,
            movieList = movieResponseEntity.movieList.asSequence().map {
                mapper.toData(it)
            }.toList(),
            totalPages = movieResponseEntity.totalPages,
            totalResults = movieResponseEntity.totalResults
        )
    }

}