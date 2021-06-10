package com.abdoam.data.mapper

import com.abdoam.data.model.MovieData
import com.abdoam.domain.entities.MovieEntity
import javax.inject.Inject


class MovieDataDomainMapper @Inject constructor() : Mapper<MovieData, MovieEntity> {
    override fun toDomain(movieData: MovieData): MovieEntity {
        return MovieEntity(
            id = movieData.id,
            posterPath = movieData.posterPath,
            releaseDate = movieData.releaseDate,
            title = movieData.title
        )
    }

    override fun toData(movieEntity: MovieEntity): MovieData {
        return MovieData(
            id = movieEntity.id,
            posterPath = movieEntity.posterPath,
            releaseDate = movieEntity.releaseDate ?: "",
            title = movieEntity.title
        )
    }

}