package com.example.sistemaexternimultimdia.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.sistemaexternimultimdia.data.db.FavoritoPokemon
import com.example.sistemaexternimultimdia.data.model.PokemonEntry
import com.example.sistemaexternimultimdia.data.repository.PokemonRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

sealed class PokemonUiState {
    object Loading : PokemonUiState()
    data class Success(val pokemons: List<PokemonEntry>) : PokemonUiState()
    data class Error(val message: String) : PokemonUiState()
}

class PokemonViewModel(private val repository: PokemonRepository) : ViewModel() {

    var uiState: PokemonUiState by mutableStateOf(PokemonUiState.Loading)
        private set

    val favoritosIds: StateFlow<List<Int>> = repository.getFavoritosIds()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    init {
        cargarPokemons()
    }

    private fun cargarPokemons() {
        viewModelScope.launch {
            uiState = PokemonUiState.Loading
            uiState = try {
                val lista = repository.getPokemonList(limit = 40)
                PokemonUiState.Success(lista)
            } catch (e: Exception) {
                PokemonUiState.Error(e.message ?: "Error desconegut")
            }
        }
    }

    fun toggleFavorito(entry: PokemonEntry, tipos: String) {
        viewModelScope.launch {
            val ids = favoritosIds.value
            if (entry.id in ids) {
                repository.removeFavorito(entry.id)
            } else {
                repository.addFavorito(
                    FavoritoPokemon(
                        id       = entry.id,
                        name     = entry.name,
                        imageUrl = entry.imageUrl,
                        tipos    = tipos
                    )
                )
            }
        }
    }
}

class PokemonViewModelFactory(private val repository: PokemonRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PokemonViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return PokemonViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
