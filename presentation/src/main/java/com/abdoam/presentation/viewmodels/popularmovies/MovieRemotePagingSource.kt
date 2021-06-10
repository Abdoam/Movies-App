package com.abdoam.presentation.viewmodels.popularmovies

import androidx.paging.PagingState
import androidx.paging.rxjava3.RxPagingSource
import com.abdoam.domain.entities.MovieResponseEntity
import com.abdoam.domain.usecases.GetPopularMovies
import com.abdoam.presentation.mapper.Mapper
import com.abdoam.presentation.model.Movie
import com.abdoam.presentation.model.MovieResponse
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject


class MovieRemotePagingSource @Inject internal constructor(
    private val movieDetailsMapper: Mapper<MovieResponse, MovieResponseEntity>,
    private val getPopularMovies: GetPopularMovies
) : RxPagingSource<Int, Movie>() {


    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        return state.anchorPosition?.let {
            state.closestPageToPosition(it)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(it)?.nextKey?.minus(1)
        }
    }

    override fun loadSingle(params: LoadParams<Int>): Single<LoadResult<Int, Movie>> {

        val pageNumber = params.key ?: 1
        return getPopularMovies
            .buildUseCase(pageNumber)
            .map {
                movieDetailsMapper.toPresentation(it)
            }
            .map {
                LoadResult.Page(
                    data = it.movieList,
                    prevKey = if (pageNumber == 1) null else pageNumber - 1,
                    nextKey = if (pageNumber == it.totalPages) null else pageNumber + 1
                ) as LoadResult<Int, Movie>
            }.onErrorReturn { LoadResult.Error(it) }
    }
}