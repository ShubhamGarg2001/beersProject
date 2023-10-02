package com.example.digimantra.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.digimantra.databinding.ItemBeerBinding
import com.example.digimantra.model.Beer

class BeersAdapter(val onItemClick: (beer: Beer?) -> Unit) :
    PagingDataAdapter<Beer, BeersAdapter.BeerViewHolder>(comparator) {

    companion object {
        private val comparator = object :
            DiffUtil.ItemCallback<Beer>() {
            override fun areItemsTheSame(oldItem: Beer, newItem: Beer): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Beer, newItem: Beer): Boolean {
                return oldItem == newItem
            }
        }
    }

    inner class BeerViewHolder(val binding: ItemBeerBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onBindViewHolder(holder: BeerViewHolder, position: Int) {
        val beer = getItem(position)
        holder.binding.beer = beer
        holder.binding.root.setOnClickListener {
            onItemClick(beer)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BeerViewHolder {
        return BeerViewHolder(
            ItemBeerBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }
}