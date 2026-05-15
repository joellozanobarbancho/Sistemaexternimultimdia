package com.example.sistemaexternimultimdia.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.*
import com.example.sistemaexternimultimdia.data.db.AppDatabase
import com.example.sistemaexternimultimdia.data.repository.PokemonRepository
import com.example.sistemaexternimultimdia.ui.screens.favoritos.PantallaFavoritos
import com.example.sistemaexternimultimdia.ui.screens.lista.PantallaLista
import com.example.sistemaexternimultimdia.ui.screens.video.PantallaVideo
import com.example.sistemaexternimultimdia.ui.viewmodel.FavoritosViewModel
import com.example.sistemaexternimultimdia.ui.viewmodel.FavoritosViewModelFactory
import com.example.sistemaexternimultimdia.ui.viewmodel.PokemonViewModel
import com.example.sistemaexternimultimdia.ui.viewmodel.PokemonViewModelFactory

@Composable
fun NavGraph() {
    val navController = rememberNavController()
    val context = LocalContext.current

    val db = remember { AppDatabase.getInstance(context) }
    val repository = remember { PokemonRepository(db.favoritosDao()) }

    val pokemonViewModel: PokemonViewModel = viewModel(
        factory = PokemonViewModelFactory(repository)
    )
    val favoritosViewModel: FavoritosViewModel = viewModel(
        factory = FavoritosViewModelFactory(repository)
    )

    val items = listOf(
        Triple(Routes.Lista.ruta, "Pokémon", Icons.AutoMirrored.Filled.List),
        Triple(Routes.Video.ruta, "Vídeo", Icons.Default.PlayArrow),
        Triple(Routes.Favoritos.ruta, "Favoritos", Icons.Default.Favorite)
    )

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Scaffold(
        bottomBar = {
            NavigationBar {
                items.forEach { (ruta, label, icon) ->
                    NavigationBarItem(
                        selected = currentRoute == ruta,
                        onClick = {
                            navController.navigate(ruta) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        },
                        icon = { Icon(icon, contentDescription = label) },
                        label = { Text(label) }
                    )
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Routes.Lista.ruta,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Routes.Lista.ruta) {
                PantallaLista(viewModel = pokemonViewModel)
            }
            composable(Routes.Video.ruta) {
                PantallaVideo()
            }
            composable(Routes.Favoritos.ruta) {
                PantallaFavoritos(viewModel = favoritosViewModel)
            }
        }
    }
}
