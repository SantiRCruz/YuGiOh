package com.example.movietechnicaltest.presentation.cards.adapters

import android.content.Context
import android.view.GestureDetector
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.core.view.GestureDetectorCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movietechnicaltest.core.hide
import com.example.movietechnicaltest.core.show
import com.example.movietechnicaltest.databinding.ItemMovieBinding
import com.example.movietechnicaltest.domain.models.Card

class MoviesAdapter(
    private val list: List<Card>,
    private val onClickItem: (Card) -> Unit,
) :
    RecyclerView.Adapter<MoviesAdapter.CardsViewHolder>() {

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
        holder.bind(list[position], onClickItem)
    }

    class CardsViewHolder(private val binding: ItemMovieBinding, private val context: Context) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Card, onClickItem: (Card) -> Unit) {
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
            binding.root.setOnClickListener {
                binding.animationView.animate().translationY(300f).alpha(0f).setDuration(0)
                    .withEndAction {
                        binding.animationView.visibility = View.VISIBLE
                        binding.animationView.animate().translationY(0f).alpha(1f).setDuration(1000)
                            .withEndAction {
                                binding.animationView.visibility = View.GONE
                            }
                    }
                onClickItem(item)

                item.isFavorite = !item.isFavorite
                if (item.isFavorite) {
                    binding.constraintLayout1.show()
                } else {
                    binding.constraintLayout1.hide()
                }
            }
        }
    }
}