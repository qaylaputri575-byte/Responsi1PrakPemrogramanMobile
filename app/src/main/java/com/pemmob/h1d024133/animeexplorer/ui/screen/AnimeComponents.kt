package com.pemmob.h1d024133.animeexplorer.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

internal data class AgeRating(
    val code: String,
    val caption: String,
    val colors: List<Color>
)

internal fun ageRatingOf(rating: String?): AgeRating {

    val code = rating?.substringBefore(" -")?.trim()
    return when (code) {
        "G" -> AgeRating("G", "Semua umur", listOf(Color(0xFF43A047), Color(0xFF00838F)))
        "PG" -> AgeRating("PG", "Anak-anak", listOf(Color(0xFF00838F), Color(0xFF3949AB)))
        "PG-13" -> AgeRating("PG-13", "13+", listOf(Color(0xFF5B4FCF), Color(0xFF8E24AA)))
        "R" -> AgeRating("R", "17+", listOf(Color(0xFFFF7043), Color(0xFFD81B60)))
        "R+" -> AgeRating("R+", "Dewasa", listOf(Color(0xFFB71C1C), Color(0xFF560027)))
        "Rx" -> AgeRating("Rx", "Dewasa", listOf(Color(0xFFB71C1C), Color(0xFF560027)))
        else -> AgeRating("NR", "Belum dinilai", listOf(Color(0xFF757575), Color(0xFF455A64)))
    }
}

@Composable
internal fun AgeRatingCover(
    rating: String?,
    modifier: Modifier = Modifier
) {
    val age = ageRatingOf(rating)
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(16.dp))
            .background(Brush.linearGradient(age.colors)),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = age.code,
                style = MaterialTheme.typography.titleLarge,
                color = Color.White,
                maxLines = 1
            )
            Text(
                text = age.caption,
                style = MaterialTheme.typography.labelMedium,
                color = Color.White.copy(alpha = 0.9f),
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
internal fun ScoreBadge(score: Double?, modifier: Modifier = Modifier) {
    val s = score ?: 0.0
    val color = when {
        s == 0.0 -> Color(0xFF757575)
        s >= 8.5 -> Color(0xFFFFA000)
        s >= 7.5 -> Color(0xFF43A047)
        else -> Color(0xFFF4511E)
    }
    Surface(
        modifier = modifier,
        shape = RoundedCornerShape(50),
        color = color,
        contentColor = Color.White
    ) {
        Text(
            text = "★ ${score ?: "-"}",
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
internal fun TagChip(
    text: String,
    containerColor: Color,
    contentColor: Color,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier,
        shape = RoundedCornerShape(50),
        color = containerColor,
        contentColor = contentColor
    ) {
        Text(
            text = text,
            modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp),
            style = MaterialTheme.typography.labelMedium
        )
    }
}

@Composable
internal fun StatItem(value: String, label: String, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = value,
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.primary
        )
        Text(
            text = label,
            style = MaterialTheme.typography.labelMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

internal fun statusLabel(status: String?): String? = when (status) {
    null -> null
    "Finished Airing" -> "Tamat"
    "Currently Airing" -> "Sedang Tayang"
    "Not yet aired" -> "Belum Tayang"
    else -> status
}

@Composable
internal fun LoadingContent(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        CircularProgressIndicator()
        Text(
            text = "Memuat anime...",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

@Composable
internal fun ErrorContent(
    message: String,
    onRetry: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text(
            text = "Oops!",
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.error
        )
        Text(
            text = message,
            style = MaterialTheme.typography.bodyLarge,
            textAlign = TextAlign.Center
        )
        Button(onClick = onRetry) {
            Text("Coba Lagi")
        }
    }
}