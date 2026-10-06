package com.pemmob.h1d024133.animeexplorer.data.repository

import com.pemmob.h1d024133.animeexplorer.data.model.Anime
import com.pemmob.h1d024133.animeexplorer.data.remote.ApiService
import com.pemmob.h1d024133.animeexplorer.data.remote.RetrofitClient
import retrofit2.HttpException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

class AnimeRepository(
    private val api: ApiService = RetrofitClient.apiService
) {
    suspend fun getTopAnime(): Result<List<Anime>> =
        runCatching { api.getTopAnime().data }
}

fun Throwable.toFriendlyMessage(): String = when (this) {
    is UnknownHostException -> "Tidak ada koneksi internet"
    is SocketTimeoutException -> "Koneksi timeout, coba lagi"
    is HttpException ->
        if (code() == 429) "Terlalu banyak request, tunggu sebentar lalu coba lagi"
        else "Server bermasalah (kode ${code()})"
    else -> message ?: "Terjadi kesalahan"
}