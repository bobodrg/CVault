package com.example.cvault.ui.detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.AssistChip
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.cvault.data.ApplicationStatus
import com.example.cvault.data.FakeJobs
import com.example.cvault.data.JobPosting
import com.example.cvault.ui.matches.ScoreBadge
import com.example.cvault.ui.theme.CVaultTheme

/** Screen 2: one posting, why it matched, and the user's application status and note. */
@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun PostingDetailScreen(
    posting: JobPosting,
    onBack: () -> Unit,
) {
    // Prototype only: plain `remember` is lost on rotation and process death.
    // That is deliberate, for the Part 4 stress test; Lab 4 moves this into a ViewModel.
    var status by remember { mutableStateOf(ApplicationStatus.NEW) }
    var note by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(posting.company) },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                },
            )
        },
    ) { innerPadding ->
        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier
                .padding(innerPadding)
                .padding(16.dp)
                .verticalScroll(rememberScrollState()),
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                ScoreBadge(score = posting.matchScore)
                Spacer(Modifier.width(16.dp))
                Column {
                    Text(posting.title, style = MaterialTheme.typography.titleLarge)
                    Text(
                        text = "${posting.location} · ${posting.source} · ${posting.publishedAt}",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                    )
                }
            }

            Text("Why it matched", style = MaterialTheme.typography.titleMedium)
            if (posting.matchedSkills.isEmpty()) {
                Text("No skills from your CV appear in this posting.")
            } else {
                FlowRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    posting.matchedSkills.forEach { skill ->
                        AssistChip(onClick = {}, label = { Text(skill) })
                    }
                }
            }

            Text("Description", style = MaterialTheme.typography.titleMedium)
            Text(posting.description)

            Text("Your status", style = MaterialTheme.typography.titleMedium)
            FlowRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                ApplicationStatus.entries.forEach { option ->
                    FilterChip(
                        selected = status == option,
                        onClick = { status = option },
                        label = { Text(option.label) },
                    )
                }
            }

            OutlinedTextField(
                value = note,
                onValueChange = { note = it },
                label = { Text("Note") },
                modifier = Modifier.fillMaxWidth(),
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PostingDetailScreenPreview() {
    CVaultTheme {
        PostingDetailScreen(posting = FakeJobs.postings.first(), onBack = {})
    }
}
