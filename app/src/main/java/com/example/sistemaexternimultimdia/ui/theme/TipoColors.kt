package com.example.sistemaexternimultimdia.ui.theme

import androidx.compose.ui.graphics.Color

fun tipoColor(tipo: String): Color = when (tipo.lowercase()) {
    "fire"     -> Color(0xFFFF6B35)
    "water"    -> Color(0xFF4FC3F7)
    "grass"    -> Color(0xFF66BB6A)
    "electric" -> Color(0xFFFFEB3B)
    "psychic"  -> Color(0xFFF48FB1)
    "ice"      -> Color(0xFF80DEEA)
    "dragon"   -> Color(0xFF7E57C2)
    "dark"     -> Color(0xFF5D4037)
    "fairy"    -> Color(0xFFF8BBD9)
    "fighting" -> Color(0xFFEF5350)
    "poison"   -> Color(0xFFAB47BC)
    "ground"   -> Color(0xFFD7A045)
    "rock"     -> Color(0xFFBCAAA4)
    "ghost"    -> Color(0xFF7986CB)
    "steel"    -> Color(0xFF90A4AE)
    "bug"      -> Color(0xFF8BC34A)
    "normal"   -> Color(0xFFBDBDBD)
    "flying"   -> Color(0xFF82B1FF)
    else       -> Color(0xFF9E9E9E)
}
