package com.pemmob.h1d024133.animeexplorer.data.model

import com.google.gson.annotations.SerializedName

data class AnimeListResponse(
    val data: List<Anime> = emptyList()
)

data class Anime(
    @SerializedName("mal_id") val malId: Int = 0,
    val title: String? = null,
    @SerializedName("title_english") val titleEnglish: String? = null,
    val type: String? = null,
    val episodes: Int? = null,
    val status: String? = null,
    val score: Double? = null,
    val year: Int? = null,
    val aired: Aired? = null,
    val rating: String? = null,
    val duration: String? = null,
    val synopsis: String? = null,
    val genres: List<Genre>? = null
) {
    // Pakai "year"; kalau null, ambil 4 digit pertama dari tanggal tayang
    val releaseYear: Int?
        get() = year ?: aired?.from?.take(4)?.toIntOrNull()
}

data class Aired(val from: String? = null)

data class Genre(val name: String? = null)