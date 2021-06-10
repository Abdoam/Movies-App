package com.abdoam.presentation.mapper

import com.abdoam.domain.entities.MovieDetailsEntity
import com.abdoam.presentation.model.MovieDetails
import javax.inject.Inject

class MovieDetailsPresentDomainMapper @Inject constructor() :
    Mapper<MovieDetails, MovieDetailsEntity> {
    override fun toDomain(movieDetails: MovieDetails): MovieDetailsEntity {
        return MovieDetailsEntity(
            budget = movieDetails.budget,
            id = movieDetails.id,
            overview = movieDetails.overview,
            popularity = movieDetails.popularity,
            posterPath = movieDetails.posterPath,
            releaseDate = movieDetails.releaseDate,
            revenue = movieDetails.revenue,
            runtime = movieDetails.runtime,
            status = movieDetails.status,
            tagline = movieDetails.tagline,
            title = movieDetails.title,
            video = movieDetails.video,
            rating = movieDetails.rating
        )
    }


    override fun toPresentation(movieDetailsEntity: MovieDetailsEntity): MovieDetails {
        return MovieDetails(
            budget = movieDetailsEntity.budget,
            id = movieDetailsEntity.id,
            overview = movieDetailsEntity.overview,
            popularity = movieDetailsEntity.popularity,
            posterPath = movieDetailsEntity.posterPath,
            releaseDate = movieDetailsEntity.releaseDate,
            revenue = movieDetailsEntity.revenue,
            runtime = movieDetailsEntity.runtime,
            status = movieDetailsEntity.status,
            tagline = movieDetailsEntity.tagline,
            title = movieDetailsEntity.title,
            video = movieDetailsEntity.video,
            rating = movieDetailsEntity.rating
        )
    }
}