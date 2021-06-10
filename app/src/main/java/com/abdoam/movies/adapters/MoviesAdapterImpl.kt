package com.abdoam.movies.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import com.abdoam.movies.R

class MoviesAdapterImpl : MoviesAdapter() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.movie_list_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = differ.currentList[position]
        holder.setData(movie)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }
}