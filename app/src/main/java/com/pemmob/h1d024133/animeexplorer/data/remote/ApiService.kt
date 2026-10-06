package com.pemmob.h1d024133.animeexplorer.data.remote

import com.pemmob.h1d024133.animeexplorer.data.model.AnimeListResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    // GET https://api.tenrai.org/v1/top/anime?limit=25&sfw=true
    @GET("top/anime")
    suspend fun getTopAnime(
        @Query("limit") limit: Int = 25,
        @Query("sfw") sfw: Boolean = true
    ): AnimeListResponse
}