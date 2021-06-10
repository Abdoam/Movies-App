package com.abdoam.movies.application

import com.abdoam.movies.di.DaggerMovieAppComponent
import com.github.pwittchen.reactivenetwork.library.rx2.ReactiveNetwork
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MovieApp : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerMovieAppComponent.builder()
            .application(this)
            .reactiveNetwork(
                ReactiveNetwork
                    .observeNetworkConnectivity(this)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
            )
            .build()
    }
}