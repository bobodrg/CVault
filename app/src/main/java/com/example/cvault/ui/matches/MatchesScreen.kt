package com.example.cvault.ui.matches

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.cvault.data.FakeJobs
import com.example.cvault.data.JobPosting
import com.example.cvault.ui.theme.CVaultTheme

/** Screen 1: today's postings, best match first. */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MatchesScreen(
    postings: List<JobPosting>,
    onPostingClick: (JobPosting) -> Unit,
) {
    Scaffold(
        topBar = { TopAppBar(title = { Text("Today's matches") }) },
    ) { innerPadding ->
        LazyColumn(
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier.padding(innerPadding),
        ) {
            items(postings.sortedByDescending { it.matchScore }, key = { it.id }) { posting ->
                PostingCard(posting = posting, onClick = { onPostingClick(posting) })
            }
        }
    }
}

@Composable
private fun PostingCard(posting: JobPosting, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(16.dp),
        ) {
            ScoreBadge(score = posting.matchScore)
            Spacer(Modifier.width(16.dp))
            Column {
                Text(posting.title, style = MaterialTheme.typography.titleMedium)
                Text(posting.company, style = MaterialTheme.typography.bodyMedium)
                Text(
                    text = posting.location,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                )
            }
        }
    }
}

/** A round badge with the match percentage: green for strong, amber for medium, grey for weak. */
@Composable
fun ScoreBadge(score: Int, modifier: Modifier = Modifier) {
    val color = when {
        score >= 80 -> Color(0xFF2E7D32)
        score >= 60 -> Color(0xFFF9A825)
        else -> Color(0xFF757575)
    }
    Surface(shape = CircleShape, color = color, modifier = modifier.size(52.dp)) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text("$score%", color = Color.White, fontWeight = FontWeight.Bold)
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun MatchesScreenPreview() {
    CVaultTheme {
        MatchesScreen(postings = FakeJobs.postings, onPostingClick = {})
    }
}
