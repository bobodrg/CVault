package com.example.cvault.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.cvault.data.FakeJobs
import com.example.cvault.ui.detail.PostingDetailScreen
import com.example.cvault.ui.matches.MatchesScreen
import kotlinx.serialization.Serializable

// Type-safe routes: each destination is a @Serializable type instead of a string.
@Serializable
object Matches

@Serializable
data class PostingDetail(val postingId: String)

@Composable
fun CVaultNavHost() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Matches) {
        composable<Matches> {
            MatchesScreen(
                postings = FakeJobs.postings,
                onPostingClick = { posting -> navController.navigate(PostingDetail(posting.id)) },
            )
        }
        composable<PostingDetail> { backStackEntry ->
            val route = backStackEntry.toRoute<PostingDetail>()
            val posting = FakeJobs.byId(route.postingId) ?: return@composable
            PostingDetailScreen(
                posting = posting,
                onBack = { navController.popBackStack() },
            )
        }
    }
}
