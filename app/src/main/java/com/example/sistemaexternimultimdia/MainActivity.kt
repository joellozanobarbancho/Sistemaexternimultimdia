package com.example.sistemaexternimultimdia

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.sistemaexternimultimdia.navigation.NavGraph
import com.example.sistemaexternimultimdia.ui.theme.SistemaexternimultimediaTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SistemaexternimultimediaTheme {
                NavGraph()
            }
        }
    }
}
