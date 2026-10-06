package com.pemmob.h1d024133.animeexplorer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.pemmob.h1d024133.animeexplorer.navigation.SetupNavGraph
import com.pemmob.h1d024133.animeexplorer.ui.theme.AnimeExplorerTheme
import com.pemmob.h1d024133.animeexplorer.viewmodel.AnimeViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AnimeExplorerTheme {
                val navController = rememberNavController()
                val viewModel: AnimeViewModel = viewModel()
                SetupNavGraph(navController = navController, viewModel = viewModel)
            }
        }
    }
}