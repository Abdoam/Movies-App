package com.abdoam.movies.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.abdoam.domain.entities.MovieDetailsEntity
import com.abdoam.domain.entities.MovieEntity
import com.abdoam.domain.entities.MovieResponseEntity
import com.abdoam.presentation.factory.ViewModelFactory
import com.abdoam.presentation.mapper.Mapper
import com.abdoam.presentation.mapper.MovieDetailsPresentDomainMapper
import com.abdoam.presentation.mapper.MoviePresentDomainMapper
import com.abdoam.presentation.mapper.MovieResponsePresentDomainMapper
import com.abdoam.presentation.model.Movie
import com.abdoam.presentation.model.MovieDetails
import com.abdoam.presentation.model.MovieResponse
import com.abdoam.presentation.viewmodels.MovieDetailVM
import com.abdoam.presentation.viewmodels.popularmovies.PopularMoviesVM
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import org.drulabs.bankbuddy.di.ViewModelKey

@Module
interface PresentationModule {

    @Binds
    fun bindsViewModelFactory(
        factory: ViewModelFactory
    ): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(MovieDetailVM::class)
    fun bindsMovieDetailViewModel(movieDetailVM: MovieDetailVM): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(PopularMoviesVM::class)
    fun bindsPopularMoviesViewModel(popularMoviesVM: PopularMoviesVM): ViewModel

    @Binds
    fun bindsMovieDetailsMapper(
        mapper: MovieDetailsPresentDomainMapper
    ): Mapper<MovieDetails, MovieDetailsEntity>

    @Binds
    fun bindsMovieMapper(
        mapper: MoviePresentDomainMapper
    ): Mapper<Movie, MovieEntity>

    @Binds
    fun bindsMovieResponseMapper(
        mapper: MovieResponsePresentDomainMapper
    ): Mapper<MovieResponse, MovieResponseEntity>

}