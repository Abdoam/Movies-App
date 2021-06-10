package com.abdoam.remote.mapper

import com.abdoam.data.model.MovieDetailsData
import com.abdoam.remote.model.MovieDetailsNetwork
import javax.inject.Inject


class MovieDetailsNetworkDataMapper @Inject constructor() :
    Mapper<MovieDetailsNetwork, MovieDetailsData> {
    override fun toData(movieDetailsNetwork: MovieDetailsNetwork): MovieDetailsData {
        return MovieDetailsData(
            budget = movieDetailsNetwork.budget,
            id = movieDetailsNetwork.id,
            overview = movieDetailsNetwork.overview,
            popularity = movieDetailsNetwork.popularity,
            posterPath = movieDetailsNetwork.posterPath ?: "",
            releaseDate = movieDetailsNetwork.releaseDate ?: "",
            revenue = movieDetailsNetwork.revenue,
            runtime = movieDetailsNetwork.runtime,
            status = movieDetailsNetwork.status,
            tagline = movieDetailsNetwork.tagline,
            title = movieDetailsNetwork.title,
            video = movieDetailsNetwork.video,
            rating = movieDetailsNetwork.rating
        )
    }

    override fun toNetwork(movieDetailsData: MovieDetailsData): MovieDetailsNetwork {
        return MovieDetailsNetwork(
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
}