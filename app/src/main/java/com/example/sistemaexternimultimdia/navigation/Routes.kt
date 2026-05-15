package com.example.sistemaexternimultimdia.navigation

sealed class Routes(val ruta: String) {
    object Lista     : Routes("lista")
    object Video     : Routes("video")
    object Favoritos : Routes("favoritos")
}
