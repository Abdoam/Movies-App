package com.abdoam.movies.di


import com.abdoam.data.model.MovieData
import com.abdoam.data.model.MovieDetailsData
import com.abdoam.data.model.MovieResponseData
import com.abdoam.data.repository.RemoteDataSource
import com.abdoam.movies.BuildConfig
import com.abdoam.remote.api.MovieService
import com.abdoam.remote.mapper.Mapper
import com.abdoam.remote.mapper.MovieDetailsNetworkDataMapper
import com.abdoam.remote.mapper.MovieNetworkDataMapper
import com.abdoam.remote.mapper.MovieResponseNetworkDataMapper
import com.abdoam.remote.model.MovieDetailsNetwork
import com.abdoam.remote.model.MovieNetwork
import com.abdoam.remote.model.MovieResponseNetwork
import com.abdoam.remote.source.RemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@Module(includes = [RemoteModule.Binders::class])
class RemoteModule {
    @Module
    interface Binders {
        @Binds
        fun bindsRemoteSource(remoteDataSourceImpl: RemoteDataSourceImpl): RemoteDataSource

        @Binds
        fun bindMovieDetailsMapper(movieDetailsMapper: MovieDetailsNetworkDataMapper): Mapper<MovieDetailsNetwork, MovieDetailsData>

        @Binds
        fun bindMovieMapper(movieNetworkDataMapper: MovieNetworkDataMapper): Mapper<MovieNetwork, MovieData>

        @Binds
        fun bindMovieResponseMapper(movieResponseDataNetworkMapper: MovieResponseNetworkDataMapper): Mapper<MovieResponseNetwork, MovieResponseData>
    }

    @Provides
    fun providesMovieService(retrofit: Retrofit): MovieService =
        retrofit.create(MovieService::class.java)

    @Provides
    fun providesRetrofit(okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .client(okHttpClient)
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BuildConfig.BASE_URL)
            .build()

    @Provides
    fun providesOkHttpClient(requestInterceptor: Interceptor): OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(requestInterceptor)
        .connectTimeout(60, TimeUnit.SECONDS)
        .build()

    @Provides
    fun providesInterceptor(): Interceptor = Interceptor { chain ->
        val url = chain.request()
            .url()
            .newBuilder()
            .addQueryParameter("api_key", BuildConfig.API_KEY)
            .build()

        val request = chain.request()
            .newBuilder()
            .url(url)
            .build()
        return@Interceptor chain.proceed(request)
    }
}