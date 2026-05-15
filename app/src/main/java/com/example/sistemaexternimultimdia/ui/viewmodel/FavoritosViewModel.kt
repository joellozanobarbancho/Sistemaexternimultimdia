package com.example.sistemaexternimultimdia.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.sistemaexternimultimdia.data.db.FavoritoPokemon
import com.example.sistemaexternimultimdia.data.repository.PokemonRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class FavoritosViewModel(private val repository: PokemonRepository) : ViewModel() {

    val favoritos: StateFlow<List<FavoritoPokemon>> = repository.getFavoritos()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    fun eliminarFavorito(id: Int) {
        viewModelScope.launch {
            repository.removeFavorito(id)
        }
    }
}

class FavoritosViewModelFactory(private val repository: PokemonRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FavoritosViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return FavoritosViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
