package com.abdoam.movies.di

import android.app.Application
import com.abdoam.movies.application.MovieApp
import com.github.pwittchen.reactivenetwork.library.rx2.Connectivity
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import io.reactivex.Observable
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        DomainModule::class,
        DataModule::class,
        RemoteModule::class,
        IdentityModule::class,
        PresentationModule::class,
        AppModule::class
    ]
)
interface MovieAppComponent : AndroidInjector<MovieApp> {
    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(app: Application): Builder

        //@BindsInstance
        //fun rxNetwork(rxNetwork: RxNetwork): Builder

        @BindsInstance
        fun reactiveNetwork(
            reactiveNetwork:
            Observable<Connectivity>
        ): Builder

        fun build(): MovieAppComponent
    }

    override fun inject(app: MovieApp)
}