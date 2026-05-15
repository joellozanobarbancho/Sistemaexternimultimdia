package com.example.sistemaexternimultimdia.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val PokeColorScheme = darkColorScheme(
    primary         = Color(0xFFCC0000),
    onPrimary       = Color.White,
    secondary       = Color(0xFFFFCB05),
    onSecondary     = Color.Black,
    background      = Color(0xFF121212),
    onBackground    = Color.White,
    surface         = Color(0xFF1E1E1E),
    onSurface       = Color.White
)

@Composable
fun SistemaexternimultimediaTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = PokeColorScheme,
        content     = content
    )
}
