package com.abdoam.presentation.mapper

import com.abdoam.domain.entities.MovieEntity
import com.abdoam.presentation.model.Movie
import javax.inject.Inject


class MoviePresentDomainMapper @Inject constructor() : Mapper<Movie, MovieEntity> {
    override fun toDomain(movie: Movie): MovieEntity {
        return MovieEntity(
            id = movie.id,
            posterPath = movie.posterPath,
            releaseDate = movie.releaseDate,
            title = movie.title
        )
    }

    override fun toPresentation(movieEntity: MovieEntity): Movie {
        return Movie(
            id = movieEntity.id,
            posterPath = movieEntity.posterPath,
            releaseDate = movieEntity.releaseDate,
            title = movieEntity.title
        )
    }

}