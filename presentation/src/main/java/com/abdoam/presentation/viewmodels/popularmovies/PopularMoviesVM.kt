package com.abdoam.presentation.viewmodels.popularmovies

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.rxjava3.cachedIn
import com.abdoam.presentation.model.Movie
import io.reactivex.rxjava3.disposables.CompositeDisposable
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject

@ExperimentalCoroutinesApi
class PopularMoviesVM @Inject internal constructor(
    popularMoviesPager: PopularMoviesPager
) : ViewModel() {
    private val compositeDisposable = CompositeDisposable()
    private val _popularMovies = MutableLiveData<PagingData<Movie>>()
    val popularMovies: LiveData<PagingData<Movie>>
        get() = _popularMovies

    init {
        compositeDisposable.add(popularMoviesPager.getPopularMoviesResultStream()
            .cachedIn(viewModelScope)
            .subscribe {
                _popularMovies.value = it
            })
    }
    // private val pageNumLiveData: MutableLiveData<Int> = MutableLiveData()
    // val popularMoviesMemory: MutableLiveData<MovieResponse> = MutableLiveData()

    /*
    fun setPageNum(pageNum: Int) {
        this.pageNumber = pageNum
        pageNumLiveData.postValue(pageNum)
    }
         */
    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }
}