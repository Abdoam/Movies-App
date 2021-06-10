package com.abdoam.remote.mapper

import com.abdoam.data.model.MovieData
import com.abdoam.remote.model.MovieNetwork
import javax.inject.Inject


class MovieNetworkDataMapper @Inject constructor() : Mapper<MovieNetwork, MovieData> {
    override fun toData(movieNetwork: MovieNetwork): MovieData {
        return MovieData(
            id = movieNetwork.id,
            posterPath = movieNetwork.posterPath ?: "",
            releaseDate = movieNetwork.releaseDate ?: "",
            title = movieNetwork.title
        )
    }

    override fun toNetwork(movieData: MovieData): MovieNetwork {
        return MovieNetwork(
            id = movieData.id,
            posterPath = movieData.posterPath,
            releaseDate = movieData.releaseDate,
            title = movieData.title
        )
    }
}