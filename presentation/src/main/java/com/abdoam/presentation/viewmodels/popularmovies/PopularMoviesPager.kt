package com.abdoam.presentation.viewmodels.popularmovies

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.rxjava3.flowable
import com.abdoam.presentation.model.Movie
import io.reactivex.rxjava3.core.Flowable
import javax.inject.Inject

class PopularMoviesPager @Inject internal constructor(
    private val movieRemotePagingSource: MovieRemotePagingSource
) {

    fun getPopularMoviesResultStream(): Flowable<PagingData<Movie>> = Pager(
        config = PagingConfig(
            pageSize = 20,
            enablePlaceholders = false,
            prefetchDistance = 1
        ), pagingSourceFactory = { movieRemotePagingSource }
    ).flowable

}