package com.abdoam.movies.ui.popularmovie

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.paging.LoadState
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import com.abdoam.movies.adapters.MoviesPagingAdapter
import com.abdoam.movies.adapters.ReposLoadStateAdapter
import com.abdoam.movies.databinding.ActivityMainBinding
import com.abdoam.movies.ui.singlemoviedetails.SingleMovieActivity
import com.abdoam.presentation.viewmodels.popularmovies.PopularMoviesVM
import com.github.pwittchen.reactivenetwork.library.rx2.Connectivity
import com.github.pwittchen.reactivenetwork.library.rx2.ReactiveNetwork
import dagger.android.AndroidInjection
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject


class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var reactiveNetwork: Observable<Connectivity>

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var popularMoviesVM: PopularMoviesVM

    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    private val moviesAdapter: MoviesPagingAdapter = MoviesPagingAdapter()

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.rvMovieList.apply {
            layoutManager = GridLayoutManager(context, 3).apply {
                spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
                    override fun getSpanSize(position: Int): Int =
                        if (MoviesPagingAdapter.Movie_VIEW_TYPE == moviesAdapter.getItemViewType(
                                position
                            )
                        ) {
                            1
                        } else 3
                }
            }
            adapter = moviesAdapter
        }

        moviesAdapter.setOnItemClickListener {
            startActivity(
                Intent(this, SingleMovieActivity::class.java).apply {
                    putExtra("movieId", it.id)
                })
        }

        popularMoviesVM = ViewModelProvider(this, viewModelFactory).get(PopularMoviesVM::class.java)
        getPopularMoviesObserve()

        // add dividers between RecyclerView's row items
        val decoration = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        binding.rvMovieList.addItemDecoration(decoration)

        initAdapter()
        compositeDisposable.add(
            ReactiveNetwork
                .observeNetworkConnectivity(this)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { moviesAdapter.retry() }
                .subscribe {
                    moviesAdapter.retry()
                })
    }

    override fun onDestroy() {
        compositeDisposable.clear()
        super.onDestroy()
    }

    private fun initAdapter() {
        binding.rvMovieList.adapter = moviesAdapter.withLoadStateHeaderAndFooter(
            header = ReposLoadStateAdapter() /*{ moviesAdapter.retry() }*/,
            footer = ReposLoadStateAdapter() /*{ moviesAdapter.retry() }*/
        )
        moviesAdapter.addLoadStateListener { loadState ->
            if (loadState.source.refresh is LoadState.NotLoading &&
                loadState.append.endOfPaginationReached &&
                moviesAdapter.itemCount < 1
            ) {
                binding.progressBarPopular.visibility = View.GONE
                binding.txtErrorPopular.visibility = View.VISIBLE
                binding.rvMovieList.visibility = View.INVISIBLE
            } /*else if (loadState.source.refresh is LoadState.NotLoading &&
                loadState.append.endOfPaginationReached
            ) {
                binding.progressBarPopular.visibility = View.GONE
                binding.txtErrorPopular.visibility = View.GONE
                binding.rvMovieList.visibility = View.VISIBLE
            }*/ else if (loadState.source.refresh is LoadState.Loading) {
                binding.progressBarPopular.visibility = View.VISIBLE
                binding.txtErrorPopular.visibility = View.GONE
                binding.rvMovieList.visibility = View.INVISIBLE
            } else if (loadState.source.refresh is LoadState.Error) {
                binding.progressBarPopular.visibility = View.GONE
                binding.txtErrorPopular.visibility = View.VISIBLE
                binding.rvMovieList.visibility = View.INVISIBLE
            } else {
                binding.progressBarPopular.visibility = View.GONE
                binding.txtErrorPopular.visibility = View.GONE
                binding.rvMovieList.visibility = View.VISIBLE
            }
        }
    }

    private fun getPopularMoviesObserve() {
        popularMoviesVM.popularMovies.observe(this) {
            moviesAdapter.submitData(lifecycle, it)
        }
    }
    /*  popularMoviesVM.popularMoviesResource.observe(this, { resource ->
          println("Observed...")
          when (resource.status) {
              Status.LOADING -> {
                  println("Loading")
                  txt_error_popular.visibility = View.GONE
                  progress_bar_popular.visibility = View.VISIBLE
              }
              Status.ERROR -> {
                  println("ERROR")
                  movieResponse = null
                  progress_bar_popular.visibility = View.GONE
                  txt_error_popular.visibility = View.VISIBLE
                  if (popularMoviesVM.pageNumber == popularMoviesVM.popularMoviesMemory.value?.page) {
                      txt_error_popular.visibility = View.GONE
                      popularMoviesVM.popularMoviesMemory.value?.let { bindUI(it) }
                  }
              }
              Status.SUCCESS -> {
                  println("Success: ${resource.data}")
                  txt_error_popular.visibility = View.GONE
                  progress_bar_popular.visibility = View.GONE
                  resource.data?.let { bindUI(it) }
              }
          }
      })
  }*/
}