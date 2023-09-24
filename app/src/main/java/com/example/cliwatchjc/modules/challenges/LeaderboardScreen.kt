package com.example.cliwatchjc.modules.challenges

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import com.example.cliwatchjc.data.challenges.Leaderboard

@Composable
fun Screen3(lifecycle: Lifecycle, leaderboardEntries: List<Leaderboard>) {
    val onResumeEventObserver = remember(lifecycle) {
        object : LifecycleEventObserver {
            override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
                // Your lifecycle event handling code here
            }
        }
    }

    DisposableEffect(lifecycle) {
        lifecycle.addObserver(onResumeEventObserver)
        onDispose { lifecycle.removeObserver(onResumeEventObserver) }
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {
        Spacer(modifier = Modifier.height(16.dp))

        LazyRow {
            itemsIndexed(leaderboardEntries) { index, entry ->
                LeaderboardCard(entry = entry)
                if (index < leaderboardEntries.size - 1) {
                    Spacer(modifier = Modifier.width(16.dp)) // Add spacing between cards
                }
            }
        }
    }
}

@Composable
fun LeaderboardCard(entry: Leaderboard) {
    // You can create a Card composable here to display the leaderboard entry
    // with the userID, name, and score.
    // Customize the appearance of the card as needed.

    Card(
        modifier = Modifier
            .width(200.dp) // Adjust the width as needed
            .height(120.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = "UserID: ${entry.userId}")
            Text(text = "Name: ${entry.userName}")
            Text(text = "Score: ${entry.score}")
        }
    }
}
