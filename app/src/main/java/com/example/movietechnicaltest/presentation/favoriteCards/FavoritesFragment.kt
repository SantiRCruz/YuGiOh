package com.example.movietechnicaltest.presentation.favoriteCards

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movietechnicaltest.R
import com.example.movietechnicaltest.core.hide
import com.example.movietechnicaltest.core.show
import com.example.movietechnicaltest.databinding.FragmentFavoritesBinding
import com.example.movietechnicaltest.domain.models.Card
import com.example.movietechnicaltest.presentation.favoriteCards.adapter.FavoriteAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoritesFragment : Fragment(R.layout.fragment_favorites) {

    private lateinit var binding: FragmentFavoritesBinding

    private val viewModel by viewModels<FavoritesViewModel>()

    private lateinit var adapter: FavoriteAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentFavoritesBinding.bind(view)
        setUpObservers()
        clickListeners()
    }

    private fun clickListeners() {
        binding.imageViewBack.setOnClickListener {
            Navigation.findNavController(binding.root).popBackStack()
        }
    }

    private fun setUpObservers() {
        viewModel.cards.observe(viewLifecycleOwner) {
            setUpCard(it)
        }

        viewModel.updateSuccess.observe(viewLifecycleOwner) {
            if (it) {

            }
        }

        viewModel.loading.observe(viewLifecycleOwner) {
            if (it) {
                binding.progress.show()
            } else {
                binding.progress.hide()
            }
        }
    }

    private fun setUpCard(data: List<Card>) {
        val adapter = FavoriteAdapter(data, onClickItem = { card ->
            onClickCard(card)
        })
        binding.rvMovies.layoutManager = LinearLayoutManager(requireContext())
        binding.rvMovies.adapter = adapter
    }

    private fun onClickCard(it: Card) {
        viewModel.onFavoriteEvent(it.id, it.isFavorite)
        viewModel.searchFavoriteCards()
    }
}