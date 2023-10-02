package com.example.digimantra.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.digimantra.databinding.LoaderItemBinding

class LoaderAdapter(val retryNow: () -> Unit) :
    LoadStateAdapter<LoaderAdapter.NetworkStateItemViewHolder>() {
    class NetworkStateItemViewHolder(
        val binding: LoaderItemBinding,
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(loadState: LoadState) {
            with(binding) {
                loader.isVisible = loadState is LoadState.Loading
                retryNow.isVisible = loadState is LoadState.Error
            }
        }
    }

    override fun onBindViewHolder(holder: NetworkStateItemViewHolder, loadState: LoadState) {
        holder.bind(loadState)
        holder.binding.retryNow.setOnClickListener {
            retryNow()
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        loadState: LoadState
    ): NetworkStateItemViewHolder {
        val binding: LoaderItemBinding = LoaderItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return NetworkStateItemViewHolder(binding)
    }
}
