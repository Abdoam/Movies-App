package com.abdoam.remote.mapper

import com.abdoam.data.model.MovieData
import com.abdoam.data.model.MovieResponseData
import com.abdoam.remote.model.MovieNetwork
import com.abdoam.remote.model.MovieResponseNetwork
import javax.inject.Inject


class MovieResponseNetworkDataMapper @Inject constructor(val mapper: Mapper<MovieNetwork, MovieData>) :
    Mapper<MovieResponseNetwork, MovieResponseData> {
    override fun toData(r: MovieResponseNetwork): MovieResponseData {
        return MovieResponseData(
            page = r.page,
            movieList = r.movieList.asSequence().map {
                mapper.toData(it)
            }.toList(),
            totalPages = r.totalPages,
            totalResults = r.totalResults
        )
    }

    override fun toNetwork(t: MovieResponseData): MovieResponseNetwork {
        return MovieResponseNetwork(
            page = t.page,
            movieList = t.movieList.asSequence().map {
                mapper.toNetwork(it)
            }.toList(),
            totalPages = t.totalPages,
            totalResults = t.totalResults
        )
    }
}