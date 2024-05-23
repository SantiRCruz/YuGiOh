package com.example.movietechnicaltest.presentation.favoriteCards.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movietechnicaltest.core.hide
import com.example.movietechnicaltest.core.show
import com.example.movietechnicaltest.databinding.ItemMovieBinding
import com.example.movietechnicaltest.domain.models.Card

class FavoriteAdapter(
    private val list: List<Card>,
) :
    RecyclerView.Adapter<FavoriteAdapter.CardsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardsViewHolder {
        val itemBinding = ItemMovieBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return CardsViewHolder(itemBinding, parent.context)
    }

    override fun getItemCount(): Int = list.size


    override fun onBindViewHolder(holder: CardsViewHolder, position: Int) {
        holder.bind(list[position])
    }

    class CardsViewHolder(private val binding: ItemMovieBinding, private val context: Context) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Card) {
            binding.constraintLayout1.hide()

            Glide.with(context)
                .load(item.imageUrl)
                .into(binding.ivPoster)
            binding.textViewTitle.text = item.name
            binding.textViewTime.text = item.archetype
            binding.textviewYear.text = item.type
            if (item.isFavorite) {
                binding.constraintLayout1.show()
            }
            binding.textviewYear.text = item.type
        }
    }
}