package com.abdoam.movies.adapters

import android.view.View
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.abdoam.movies.BuildConfig
import com.abdoam.movies.R
import com.abdoam.presentation.model.Movie
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.movie_list_item.view.*

abstract class MoviesAdapter : RecyclerView.Adapter<MoviesAdapter.MovieViewHolder>() {

    private val differCallback = object : DiffUtil.ItemCallback<Movie>() {
        override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)

    private var onItemClickListener: ((Movie) -> Unit)? = null

    fun setOnItemClickListener(listener: (Movie) -> Unit) {
        onItemClickListener = listener
    }

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
}