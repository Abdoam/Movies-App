package com.abdoam.movies.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.recyclerview.widget.RecyclerView
import com.abdoam.movies.R
import com.abdoam.movies.databinding.ReposLoadStateFooterViewItemBinding

class ReposLoadStateViewHolder(
    private val binding: ReposLoadStateFooterViewItemBinding,
    /* retry: () -> Unit*/
) : RecyclerView.ViewHolder(binding.root) {

/*    init {
        binding.retryButton.setOnClickListener { retry.invoke() }
    }*/

    fun bind(loadState: LoadState) {
        if (loadState is LoadState.Error) {
            binding.errorMsg.text =
                binding.root.context.getText(R.string.presentation_connection_error)/*loadState.error.localizedMessage*/
        }
        binding.progressBar.visibility =
            if (loadState is LoadState.Loading) View.VISIBLE else View.GONE
        /*binding.retryButton.visibility =
            if (loadState is LoadState.Error) View.VISIBLE else View.GONE*/
        binding.errorMsg.visibility = if (loadState is LoadState.Error) View.VISIBLE else View.GONE
    }

    companion object {
        fun create(parent: ViewGroup/*, retry: () -> Unit*/): ReposLoadStateViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.repos_load_state_footer_view_item, parent, false)
            val binding = ReposLoadStateFooterViewItemBinding.bind(view)
            return ReposLoadStateViewHolder(binding/*, retry*/)
        }
    }
}
