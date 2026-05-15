package com.example.sistemaexternimultimdia.data.db

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoritosDao {

    @Query("SELECT * FROM favoritos ORDER BY name ASC")
    fun getAll(): Flow<List<FavoritoPokemon>>

    @Query("SELECT id FROM favoritos")
    fun getAllIds(): Flow<List<Int>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(favorito: FavoritoPokemon)

    @Delete
    suspend fun delete(favorito: FavoritoPokemon)

    @Query("DELETE FROM favoritos WHERE id = :id")
    suspend fun deleteById(id: Int)
}
