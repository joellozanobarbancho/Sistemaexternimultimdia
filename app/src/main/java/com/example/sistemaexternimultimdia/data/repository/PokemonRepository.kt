package com.example.sistemaexternimultimdia.data.repository

import com.example.sistemaexternimultimdia.data.db.FavoritoPokemon
import com.example.sistemaexternimultimdia.data.db.FavoritosDao
import com.example.sistemaexternimultimdia.data.model.PokemonDetail
import com.example.sistemaexternimultimdia.data.model.PokemonEntry
import com.example.sistemaexternimultimdia.data.network.RetrofitClient
import kotlinx.coroutines.flow.Flow

class PokemonRepository(private val favoritosDao: FavoritosDao) {

    suspend fun getPokemonList(limit: Int = 40, offset: Int = 0): List<PokemonEntry> {
        return RetrofitClient.apiService.getPokemonList(limit, offset).results
    }

    suspend fun getPokemonDetail(name: String): PokemonDetail {
        return RetrofitClient.apiService.getPokemonDetail(name)
    }

    fun getFavoritos(): Flow<List<FavoritoPokemon>> = favoritosDao.getAll()

    fun getFavoritosIds(): Flow<List<Int>> = favoritosDao.getAllIds()

    suspend fun addFavorito(favorito: FavoritoPokemon) = favoritosDao.insert(favorito)

    suspend fun removeFavorito(id: Int) = favoritosDao.deleteById(id)
}
