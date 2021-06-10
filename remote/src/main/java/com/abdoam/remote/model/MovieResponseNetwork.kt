package com.abdoam.remote.model


import com.google.gson.annotations.SerializedName

data class MovieResponseNetwork(
    val page: Int,
    @SerializedName("results")
    val movieList: List<MovieNetwork>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int
)