package com.example.cliwatchjc.modules.challenges

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.cliwatchjc.data.challenges.Leaderboard

@Composable
fun LeaderboardTab(leaderboardData: List<Leaderboard>) {
    // You can sort the leaderboard data by total marks in descending order
    val sortedLeaderboard = leaderboardData.sortedByDescending { it.totalMarks }

    LazyColumn{
        items(sortedLeaderboard) { entry ->
            LeaderboardEntryRow(entry)
        }
    }
}
@Composable
fun LeaderboardEntryRow(entry: Leaderboard) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = entry.username)
        Text(text = entry.totalMarks.toString())
    }
}

