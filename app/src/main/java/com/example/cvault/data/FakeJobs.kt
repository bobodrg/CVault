package com.example.cvault.data

import java.time.LocalDate

/**
 * Hardcoded postings for the Lab 3 prototype. No network, no database:
 * Lab 5 replaces this with Remotive/Arbeitnow, Lab 6 with Room.
 */
object FakeJobs {

    private val today = LocalDate.of(2026, 10, 25)

    val postings: List<JobPosting> = listOf(
        JobPosting(
            id = "rm-1001",
            title = "Junior Android Developer",
            company = "Fieldnote Labs",
            location = "Bucharest, Romania",
            remote = false,
            tags = listOf("Kotlin", "Jetpack Compose", "Room", "Git"),
            description = "Build offline-first features for our field-data app. " +
                "You will work with Kotlin, Compose and Room, and ship to thousands of users.",
            url = "https://example.com/jobs/rm-1001",
            publishedAt = today,
            source = "Remotive",
            matchScore = 92,
            matchedSkills = listOf("Kotlin", "Jetpack Compose", "Git"),
        ),
        JobPosting(
            id = "an-2002",
            title = "Mobile Engineer (Android)",
            company = "Tramline",
            location = "Remote, EU",
            remote = true,
            tags = listOf("Kotlin", "Coroutines", "Retrofit", "CI"),
            description = "Join a small team building a transit companion used underground. " +
                "Offline caching and background sync are core to the product.",
            url = "https://example.com/jobs/an-2002",
            publishedAt = today,
            source = "Arbeitnow",
            matchScore = 85,
            matchedSkills = listOf("Kotlin", "Coroutines"),
        ),
        JobPosting(
            id = "rm-1003",
            title = "Software Engineer, Distributed Systems",
            company = "Quorum Data",
            location = "Remote, worldwide",
            remote = true,
            tags = listOf("Go", "Kubernetes", "gRPC", "Linux"),
            description = "Work on the replication layer of a distributed database.",
            url = "https://example.com/jobs/rm-1003",
            publishedAt = today.minusDays(1),
            source = "Remotive",
            matchScore = 71,
            matchedSkills = listOf("Linux", "Distributed systems"),
        ),
        JobPosting(
            id = "an-2004",
            title = "Kotlin Backend Developer",
            company = "Ledgerly",
            location = "Cluj-Napoca, Romania",
            remote = false,
            tags = listOf("Kotlin", "Ktor", "PostgreSQL"),
            description = "Design REST APIs for our accounting platform.",
            url = "https://example.com/jobs/an-2004",
            publishedAt = today.minusDays(1),
            source = "Arbeitnow",
            matchScore = 64,
            matchedSkills = listOf("Kotlin", "REST"),
        ),
        JobPosting(
            id = "rm-1005",
            title = "QA Automation Intern",
            company = "Brightpath",
            location = "Bucharest, Romania",
            remote = false,
            tags = listOf("Espresso", "JUnit", "GitHub Actions"),
            description = "Write UI and unit tests for our Android and iOS apps.",
            url = "https://example.com/jobs/rm-1005",
            publishedAt = today.minusDays(2),
            source = "Remotive",
            matchScore = 58,
            matchedSkills = listOf("JUnit"),
        ),
        JobPosting(
            id = "an-2006",
            title = "Frontend Developer (React)",
            company = "Pixelmint",
            location = "Remote, EU",
            remote = true,
            tags = listOf("React", "TypeScript", "CSS"),
            description = "Build the web dashboard for our design tool.",
            url = "https://example.com/jobs/an-2006",
            publishedAt = today.minusDays(3),
            source = "Arbeitnow",
            matchScore = 37,
            matchedSkills = emptyList(),
        ),
    )

    fun byId(id: String): JobPosting? = postings.firstOrNull { it.id == id }
}
