package com.abdoam.data.model


data class MovieResponseData(
    val page: Int,
    val movieList: List<MovieData>,
    val totalPages: Int,
    val totalResults: Int
)