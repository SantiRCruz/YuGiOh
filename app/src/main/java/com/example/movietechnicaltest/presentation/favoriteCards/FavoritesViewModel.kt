package com.example.movietechnicaltest.presentation.favoriteCards

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movietechnicaltest.domain.models.Card
import com.example.movietechnicaltest.domain.repository.CardsRepo
import com.example.movietechnicaltest.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(
    private val repository: CardsRepo
) : ViewModel() {

    private val _cards by lazy { MutableLiveData<List<Card>>() }
    val cards: LiveData<List<Card>> = _cards

    private val _loading by lazy { MutableLiveData<Boolean>() }
    val loading: LiveData<Boolean> = _loading

    private val _updateSuccess by lazy { MutableLiveData<Boolean>() }
    val updateSuccess: LiveData<Boolean> = _updateSuccess

    init {
        searchFavoriteCards()
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

    fun searchFavoriteCards() {
        viewModelScope.launch {
            repository
                .searchFavoriteCards()
                .collect { result ->
                    when (result) {
                        is Resource.Success -> {
                            result.data?.let { cards ->
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