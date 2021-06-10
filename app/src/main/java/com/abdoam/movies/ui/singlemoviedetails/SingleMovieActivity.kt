package com.abdoam.movies.ui.singlemoviedetails

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.abdoam.movies.BuildConfig
import com.abdoam.movies.R
import com.abdoam.presentation.model.MovieDetails
import com.abdoam.presentation.model.Status
import com.abdoam.presentation.viewmodels.MovieDetailVM
import com.bumptech.glide.Glide
import com.github.pwittchen.reactivenetwork.library.rx2.Connectivity
import com.github.pwittchen.reactivenetwork.library.rx2.ReactiveNetwork
import dagger.android.AndroidInjection
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_single_movie.*
import java.text.NumberFormat
import java.util.*
import javax.inject.Inject

class SingleMovieActivity : AppCompatActivity() {
    //@Inject
    //lateinit var rxNetwork: RxNetwork

    @Inject
    lateinit var reactiveNetwork: Observable<Connectivity>

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var movieDetailVM: MovieDetailVM

    private var userInfo: MovieDetails? = null

    private val compositeDisposable: CompositeDisposable = CompositeDisposable()


    private var movieId: Int = 0

    @SuppressLint("CheckResult")
    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_single_movie)
        movieDetailVM = ViewModelProvider(this, viewModelFactory).get(MovieDetailVM::class.java)
        movieId = intent.extras?.getInt("movieId") ?: 0
        compositeDisposable.add(
            ReactiveNetwork
                .observeNetworkConnectivity(this)
                .subscribeOn(Schedulers.io())
                .doOnSubscribe { getMovieDetailsObserve() }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    movieDetailVM.setMovieId(movieId)
                })
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
        }
        return super.onContextItemSelected(item)
    }

    override fun onDestroy() {
        compositeDisposable.dispose()
        super.onDestroy()
    }

    private fun getMovieDetailsObserve() {
        movieDetailVM.movieDetailsResource.observe(this, { resource ->
            println("Observed...")
            when (resource.status) {
                Status.LOADING -> {
                    println("Loading")
                    progress_bar.visibility = View.VISIBLE
                    txt_error.visibility = View.GONE
                    linearLayout.visibility = View.GONE
                }
                Status.ERROR -> {
                    println("ERROR")
                    userInfo = null
                    progress_bar.visibility = View.GONE
                    txt_error.visibility = View.VISIBLE
                    linearLayout.visibility = View.GONE
                    if (movieDetailVM.movieIdentifier == movieDetailVM.movieDetailsMemory.value?.id) {
                        txt_error.visibility = View.GONE
                        linearLayout.visibility = View.VISIBLE
                        movieDetailVM.movieDetailsMemory.value?.let { bindUI(it) }
                    }
                }
                Status.SUCCESS -> {
                    println("Success: ${resource.data}")
                    resource.data?.let { bindUI(it) }
                }
            }
        })
    }

    private fun bindUI(data: MovieDetails) {
        data.let {
            linearLayout.visibility = View.VISIBLE
            progress_bar.visibility = View.GONE
            txt_error.visibility = View.GONE
            movie_title.text = it.title
            movie_overview.text = it.overview
            movie_rating.text = it.rating.toString()
            movie_release_date.text = it.releaseDate
            val formatCurrency = NumberFormat.getCurrencyInstance(Locale.US)
            movie_budget.text = it.let { formatCurrency.format(it.budget) }
            movie_revenue.text = it.let { formatCurrency.format(it.revenue) }
            movie_runtime.text = it.runtime.toString()
            movie_tagline.text = it.tagline
            Glide.with(this)
                .load(BuildConfig.POSTER_BASE_URL + it.posterPath)
                .placeholder(R.drawable.poster_placeholder)
                .error(R.drawable.poster_placeholder)
                .into(iv_movie_poster)
            userInfo = it
        }
    }
}