package com.abdoam.movies.di

import com.abdoam.data.mapper.Mapper
import com.abdoam.data.mapper.MovieDataDomainMapper
import com.abdoam.data.mapper.MovieDetailsDataDomainMapper
import com.abdoam.data.mapper.MovieResponseDataDomainMapper
import com.abdoam.data.model.MovieData
import com.abdoam.data.model.MovieDetailsData
import com.abdoam.data.model.MovieResponseData
import com.abdoam.data.repository.MovieRepositoryImpl
import com.abdoam.domain.entities.MovieDetailsEntity
import com.abdoam.domain.entities.MovieEntity
import com.abdoam.domain.entities.MovieResponseEntity
import com.abdoam.domain.repository.MovieRepository
import dagger.Binds
import dagger.Module


@Module
interface DataModule {

    @Binds
    fun bindsRepository(
        repoImpl: MovieRepositoryImpl
    ): MovieRepository

    @Binds
    fun bindsMovieDetailsMapper(
        mapper: MovieDetailsDataDomainMapper
    ): Mapper<MovieDetailsData, MovieDetailsEntity>

    @Binds
    fun bindsMovieMapper(
        mapper: MovieDataDomainMapper
    ): Mapper<MovieData, MovieEntity>

    @Binds
    fun bindsMovieResponseMapper(
        mapper: MovieResponseDataDomainMapper
    ): Mapper<MovieResponseData, MovieResponseEntity>
}