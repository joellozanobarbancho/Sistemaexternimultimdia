package com.example.sistemaexternimultimdia.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favoritos")
data class FavoritoPokemon(
    @PrimaryKey val id: Int,
    val name: String,
    val imageUrl: String,
    val tipos: String
)
