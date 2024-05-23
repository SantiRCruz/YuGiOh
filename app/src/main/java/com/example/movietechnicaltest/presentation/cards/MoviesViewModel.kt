package com.example.movietechnicaltest.presentation.cards

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movietechnicaltest.domain.models.Card
import com.example.movietechnicaltest.domain.repository.CardsRepo
import com.example.movietechnicaltest.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val repository: CardsRepo
) : ViewModel() {

    private val _cards by lazy { MutableLiveData<List<Card>>() }
    val cards: LiveData<List<Card>> = _cards

    private val _loading by lazy { MutableLiveData<Boolean>() }
    val loading: LiveData<Boolean> = _loading

    private val _updateSuccess by lazy { MutableLiveData<Boolean>() }
    val updateSuccess: LiveData<Boolean> = _updateSuccess

    private var searchJob: Job? = null

    init {
        getCards()
    }

    fun onSearchEvent(query: String) {
        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            delay(500L)
            getCards(query = query)
        }
    }

    fun onFavoriteEvent(
        id: Int, isFavorite: Boolean
    ) {
        viewModelScope.launch {
            repository
                .updateIsFavorite(
                    id = id,
                    isFavorite = !isFavorite
                )
                .collect { result ->
                    when (result) {
                        is Resource.Success -> {
                            _updateSuccess.postValue(true)
                        }

                        is Resource.Error -> Unit

                        is Resource.Loading -> {
                            _loading.postValue(result.isLoading)
                        }
                    }
                }
        }
    }

    private fun getCards(
        query: String = "",
        fetchFromRemote: Boolean = false
    ) {
        viewModelScope.launch {
            repository
                .getCards(
                    fetchFromRemote = fetchFromRemote,
                    query = query
                )
                .collect { result ->
                    when (result) {
                        is Resource.Success -> {
                            result.data?.let { cards ->
                                Log.e("getCards111: ", cards.toString())
                                _cards.postValue(cards)
                            }
                        }

                        is Resource.Error -> Unit

                        is Resource.Loading -> {
                            _loading.postValue(result.isLoading)
                        }
                    }
                }
        }
    }
}