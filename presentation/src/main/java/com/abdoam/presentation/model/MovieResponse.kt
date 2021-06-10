package com.abdoam.presentation.model


data class MovieResponse(
    val page: Int,
    val movieList: List<Movie>,
    val totalPages: Int,
    val totalResults: Int
)