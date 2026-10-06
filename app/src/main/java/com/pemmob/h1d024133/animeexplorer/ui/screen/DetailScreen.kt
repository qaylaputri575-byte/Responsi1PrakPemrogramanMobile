package com.pemmob.h1d024133.animeexplorer.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.pemmob.h1d024133.animeexplorer.data.model.Anime
import com.pemmob.h1d024133.animeexplorer.ui.theme.GradientEnd
import com.pemmob.h1d024133.animeexplorer.ui.theme.GradientStart

@Composable
fun DetailScreen(
    anime: Anime?,
    onBackClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        if (anime == null) {
            IconButton(
                onClick = onBackClick,
                modifier = Modifier.statusBarsPadding()
            ) {
                Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Kembali")
            }
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text("Data anime tidak tersedia")
            }
        } else {
            DetailContent(anime = anime, onBackClick = onBackClick)
        }
    }
}

@Composable
private fun DetailContent(
    anime: Anime,
    onBackClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(bottomStart = 32.dp, bottomEnd = 32.dp))
                .background(Brush.linearGradient(listOf(GradientStart, GradientEnd)))
        ) {
            Column(
                modifier = Modifier
                    .statusBarsPadding()
                    .padding(horizontal = 16.dp)
                    .padding(bottom = 24.dp)
            ) {
                IconButton(onClick = onBackClick) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Kembali",
                        tint = Color.White
                    )
                }
                Spacer(Modifier.height(8.dp))
                ScoreBadge(score = anime.score)
                Spacer(Modifier.height(12.dp))
                Text(
                    text = anime.title ?: "Tanpa judul",
                    style = MaterialTheme.typography.headlineMedium,
                    color = Color.White
                )
                anime.titleEnglish?.let {
                    Spacer(Modifier.height(4.dp))
                    Text(
                        text = it,
                        style = MaterialTheme.typography.titleMedium,
                        color = Color.White.copy(alpha = 0.85f)
                    )
                }
            }
        }

        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surface
                ),
                elevation = CardDefaults.cardElevation(defaultElevation = 3.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 18.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    StatItem("★ ${anime.score ?: "-"}", "Rating")
                    StatItem("${anime.releaseYear ?: "-"}", "Tahun Rilis")
                    StatItem("${anime.episodes ?: "-"}", "Episode")
                }
            }

            Row(
                modifier = Modifier.horizontalScroll(rememberScrollState()),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                anime.type?.let {
                    TagChip(
                        text = it,
                        containerColor = MaterialTheme.colorScheme.secondaryContainer,
                        contentColor = MaterialTheme.colorScheme.onSecondaryContainer
                    )
                }
                val age = ageRatingOf(anime.rating)
                TagChip(
                    text = "${age.code} · ${age.caption}",
                    containerColor = age.colors.first(),
                    contentColor = Color.White
                )
                statusLabel(anime.status)?.let {
                    TagChip(
                        text = it,
                        containerColor = MaterialTheme.colorScheme.tertiaryContainer,
                        contentColor = MaterialTheme.colorScheme.onTertiaryContainer
                    )
                }
                anime.duration?.let {
                    TagChip(
                        text = it,
                        containerColor = MaterialTheme.colorScheme.surfaceVariant,
                        contentColor = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }

            val genreNames = anime.genres.orEmpty().mapNotNull { it.name }
            if (genreNames.isNotEmpty()) {
                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    Text(
                        text = "Genre",
                        style = MaterialTheme.typography.titleMedium
                    )
                    Row(
                        modifier = Modifier.horizontalScroll(rememberScrollState()),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        genreNames.forEach { name ->
                            TagChip(
                                text = name,
                                containerColor = MaterialTheme.colorScheme.primaryContainer,
                                contentColor = MaterialTheme.colorScheme.onPrimaryContainer
                            )
                        }
                    }
                }
            }

            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surface
                ),
                elevation = CardDefaults.cardElevation(defaultElevation = 3.dp)
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(
                        text = "Sinopsis",
                        style = MaterialTheme.typography.titleMedium
                    )
                    Text(
                        text = anime.synopsis ?: "Sinopsis tidak tersedia.",
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
            }

            Spacer(Modifier.navigationBarsPadding())
        }
    }
}