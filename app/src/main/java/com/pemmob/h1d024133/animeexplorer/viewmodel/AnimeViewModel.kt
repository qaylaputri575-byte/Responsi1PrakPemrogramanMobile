package com.pemmob.h1d024133.animeexplorer.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pemmob.h1d024133.animeexplorer.data.model.Anime
import com.pemmob.h1d024133.animeexplorer.data.repository.AnimeRepository
import com.pemmob.h1d024133.animeexplorer.data.repository.toFriendlyMessage
import kotlinx.coroutines.launch

class AnimeViewModel : ViewModel() {

    private val repository = AnimeRepository()

    // State untuk Home: Loading / Success(list) / Error(pesan)
    var uiState: UiState<List<Anime>> by mutableStateOf(UiState.Loading)
        private set

    // Anime yang diklik di Home, dibaca oleh Detail
    var selectedAnime: Anime? by mutableStateOf(null)
        private set

    init {
        fetchAnimeList()
    }

    fun fetchAnimeList() {
        viewModelScope.launch {
            uiState = UiState.Loading
            repository.getTopAnime()
                .onSuccess { uiState = UiState.Success(it) }
                .onFailure { uiState = UiState.Error(it.toFriendlyMessage()) }
        }
    }

    fun selectAnime(anime: Anime) {
        selectedAnime = anime
    }
}