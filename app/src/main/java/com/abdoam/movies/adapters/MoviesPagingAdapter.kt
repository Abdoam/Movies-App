package com.abdoam.movies.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.abdoam.movies.BuildConfig
import com.abdoam.movies.R
import com.abdoam.presentation.model.Movie
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.movie_list_item.view.*

class MoviesPagingAdapter :
    PagingDataAdapter<Movie, MoviesPagingAdapter.MovieViewHolder>(COMPARATOR) {

    inner class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun setData(movie: Movie) {
            itemView.apply {
                Glide.with(this).load(BuildConfig.POSTER_BASE_URL + movie.posterPath)
                    .placeholder(R.drawable.poster_placeholder)
                    .error(R.drawable.poster_placeholder)
                    .into(cv_iv_movie_poster)
                cv_movie_title.text = movie.title
                cv_movie_release_date.text = movie.releaseDate
                setOnClickListener {
                    onItemClickListener?.let { it(movie) }
                }
            }
        }
    }

    private var onItemClickListener: ((Movie) -> Unit)? = null

    fun setOnItemClickListener(listener: (Movie) -> Unit) {
        onItemClickListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.movie_list_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = getItem(position)
        movie?.let { holder.setData(it) }
    }

    companion object {
        const val NETWORK_VIEW_TYPE = 1
        const val Movie_VIEW_TYPE = 0

        private val COMPARATOR = object : DiffUtil.ItemCallback<Movie>() {
            override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun getItemViewType(position: Int): Int =
        if (position == itemCount) NETWORK_VIEW_TYPE else Movie_VIEW_TYPE

}