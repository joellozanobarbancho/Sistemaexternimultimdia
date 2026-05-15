package com.example.sistemaexternimultimdia.data.model

import com.google.gson.annotations.SerializedName

data class PokemonListResponse(
    @SerializedName("results") val results: List<PokemonEntry>,
    @SerializedName("next")   val next: String?
)

data class PokemonEntry(
    @SerializedName("name") val name: String,
    @SerializedName("url")  val url: String
) {
    val id: Int get() = url.trimEnd('/').substringAfterLast('/').toIntOrNull() ?: 0
    val imageUrl: String get() =
        "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/$id.png"
}

data class PokemonDetail(
    @SerializedName("id")      val id: Int,
    @SerializedName("name")    val name: String,
    @SerializedName("types")   val types: List<PokemonTypeSlot>,
    @SerializedName("stats")   val stats: List<PokemonStatSlot>,
    @SerializedName("height")  val height: Int,
    @SerializedName("weight")  val weight: Int
)

data class PokemonTypeSlot(
    @SerializedName("type") val type: PokemonTypeName
)

data class PokemonTypeName(
    @SerializedName("name") val name: String
)

data class PokemonStatSlot(
    @SerializedName("base_stat") val baseStat: Int,
    @SerializedName("stat")      val stat: PokemonStatName
)

data class PokemonStatName(
    @SerializedName("name") val name: String
)
