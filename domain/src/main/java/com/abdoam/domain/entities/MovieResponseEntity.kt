package com.abdoam.domain.entities


data class MovieResponseEntity(
    val page: Int,
    val movieList: List<MovieEntity>,
    val totalPages: Int,
    val totalResults: Int
)