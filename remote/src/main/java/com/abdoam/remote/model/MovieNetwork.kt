package com.abdoam.remote.model


import com.google.gson.annotations.SerializedName

data class MovieNetwork(
    val id: Int,
    @SerializedName("poster_path")
    val posterPath: String? = null,
    @SerializedName("release_date")
    val releaseDate: String? = null,
    val title: String,
)