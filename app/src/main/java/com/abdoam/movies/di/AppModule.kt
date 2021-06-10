package com.abdoam.movies.di

import android.app.Application
import android.content.Context
import com.abdoam.movies.ui.popularmovie.MainActivity
import com.abdoam.movies.ui.singlemoviedetails.SingleMovieActivity
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class AppModule {

    @Binds
    abstract fun bindContext(application: Application): Context

    //@Binds
    //abstract fun bindMoviesAdapter(moviesAdapter: MoviesPagingAdapter): MoviesAdapter

    @ContributesAndroidInjector
    internal abstract fun contributesMainActivity(): MainActivity

    @ContributesAndroidInjector
    internal abstract fun contributesSingleMovieActivity(): SingleMovieActivity
}