package com.example.cvault.data

import java.time.LocalDate

/** A job posting fetched from a job board, with its match score against the user's CV. */
data class JobPosting(
    val id: String,
    val title: String,
    val company: String,
    val location: String,
    val remote: Boolean,
    val tags: List<String>,
    val description: String,
    val url: String,
    val publishedAt: LocalDate,
    val source: String,
    /** 0–100: how similar the posting is to the CV. Hardcoded in the prototype. */
    val matchScore: Int,
    /** The CV skills that also appear in the posting. */
    val matchedSkills: List<String>,
)

enum class ApplicationStatus(val label: String) {
    NEW("New"),
    SAVED("Saved"),
    APPLIED("Applied"),
    INTERVIEWING("Interviewing"),
    REJECTED("Rejected"),
}
