package com.pemmob.h1d024133.animeexplorer.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.pemmob.h1d024133.animeexplorer.ui.screen.DetailScreen
import com.pemmob.h1d024133.animeexplorer.ui.screen.HomeScreen
import com.pemmob.h1d024133.animeexplorer.viewmodel.AnimeViewModel

object Routes {
    const val HOME = "home"
    const val DETAIL = "detail"
}

@Composable
fun SetupNavGraph(
    navController: NavHostController,
    viewModel: AnimeViewModel
) {
    NavHost(
        navController = navController,
        startDestination = Routes.HOME
    ) {
        composable(Routes.HOME) {
            HomeScreen(
                uiState = viewModel.uiState,
                onAnimeClick = { anime ->
                    viewModel.selectAnime(anime)
                    navController.navigate(Routes.DETAIL)
                },
                onRetry = { viewModel.fetchAnimeList() }
            )
        }

        composable(Routes.DETAIL) {
            DetailScreen(
                anime = viewModel.selectedAnime,
                onBackClick = { navController.popBackStack() }
            )
        }
    }
}