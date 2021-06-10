package com.abdoam.data.mapper

import com.abdoam.data.model.MovieDetailsData
import com.abdoam.domain.entities.MovieDetailsEntity
import javax.inject.Inject

class MovieDetailsDataDomainMapper @Inject constructor() :
    Mapper<MovieDetailsData, MovieDetailsEntity> {
    override fun toDomain(movieDetailsData: MovieDetailsData): MovieDetailsEntity {
        return MovieDetailsEntity(
            budget = movieDetailsData.budget,
            id = movieDetailsData.id,
            overview = movieDetailsData.overview,
            popularity = movieDetailsData.popularity,
            posterPath = movieDetailsData.posterPath,
            releaseDate = movieDetailsData.releaseDate,
            revenue = movieDetailsData.revenue,
            runtime = movieDetailsData.runtime,
            status = movieDetailsData.status,
            tagline = movieDetailsData.tagline,
            title = movieDetailsData.title,
            video = movieDetailsData.video,
            rating = movieDetailsData.rating
        )
    }

    override fun toData(movieDetailsEntity: MovieDetailsEntity): MovieDetailsData {
        return MovieDetailsData(
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