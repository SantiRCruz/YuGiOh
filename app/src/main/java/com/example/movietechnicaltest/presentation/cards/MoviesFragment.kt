package com.example.movietechnicaltest.presentation.cards

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movietechnicaltest.R
import com.example.movietechnicaltest.databinding.FragmentMoviesBinding
import com.example.movietechnicaltest.core.hide
import com.example.movietechnicaltest.core.show
import com.example.movietechnicaltest.domain.models.Card
import com.example.movietechnicaltest.presentation.cards.adapters.MoviesAdapter
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MoviesFragment : Fragment(R.layout.fragment_movies) {

    private lateinit var binding: FragmentMoviesBinding

    private val viewModel by viewModels<MoviesViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMoviesBinding.bind(view)
        setUpObservers()
        clickListeners()
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

    private fun clickListeners() {
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {

                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                viewModel.onSearchEvent(newText.toString())
                return true
            }
        })

        binding.animationView.setOnClickListener {
            val action = MoviesFragmentDirections.actionMoviesFragmentToFavoritesFragment()
            Navigation.findNavController(binding.root).navigate(action)
        }
    }

    private fun setUpCard(data: List<Card>) {
        val adapter = MoviesAdapter(data, onClickItem = { onClickCard(it) })
        binding.rvMovies.layoutManager = LinearLayoutManager(requireContext())
        binding.rvMovies.adapter = adapter
    }


    private fun onClickCard(it: Card) {
        viewModel.onFavoriteEvent(it.id, it.isFavorite)
    }

}