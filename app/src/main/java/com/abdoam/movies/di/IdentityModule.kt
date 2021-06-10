package com.abdoam.movies.di

import com.abdoam.presentation.qualifier.MovieDefaultID
import com.abdoam.presentation.qualifier.PageDefaultNum
import dagger.Module
import dagger.Provides

@Module
class IdentityModule {

    @Provides
    @MovieDefaultID
    fun providesMovieDefaultID(): Int = 500

    @Provides
    @PageDefaultNum
    fun providesPageDefaultNum(): Int = 1
}