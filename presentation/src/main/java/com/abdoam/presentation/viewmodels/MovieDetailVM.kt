package com.abdoam.presentation.viewmodels

import androidx.lifecycle.*
import com.abdoam.domain.entities.MovieDetailsEntity
import com.abdoam.domain.usecases.GetMovieDetailsInfo
import com.abdoam.presentation.mapper.Mapper
import com.abdoam.presentation.model.MovieDetails
import com.abdoam.presentation.model.Resource
import com.abdoam.presentation.qualifier.MovieDefaultID
import io.reactivex.rxjava3.core.BackpressureStrategy
import javax.inject.Inject

class MovieDetailVM @Inject internal constructor(
    @MovieDefaultID var movieIdentifier: Int,
    private val movieDetailsMapper: Mapper<MovieDetails, MovieDetailsEntity>,
    private val getMovieDetailsInfo: GetMovieDetailsInfo
) : ViewModel() {

    private val movieIdLiveData: MutableLiveData<Int> = MutableLiveData()
    val movieDetailsMemory: MutableLiveData<MovieDetails> = MutableLiveData()
    fun setMovieId(movieId: Int) {
        this.movieIdentifier = movieId
        movieIdLiveData.postValue(movieId)
    }

    val movieDetailsResource: LiveData<Resource<MovieDetails>> =
        Transformations.switchMap(movieIdLiveData)
        { newMovieId ->
            getMovieDetailsInfo
                .buildUseCase(newMovieId)
                .map {
                    movieDetailsMapper.toPresentation(it)
                }
                .map {
                    movieDetailsMemory.postValue(it)
                    Resource.success(it)
                }
                .onErrorReturn { e -> Resource.error(e.localizedMessage) }
                .startWithItem(Resource.loading())
                .toFlowable(BackpressureStrategy.LATEST)
                .toLiveData()
        }
}