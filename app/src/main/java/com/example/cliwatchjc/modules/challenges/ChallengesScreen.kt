package com.example.cliwatchjc.modules.challenges

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.cliwatchjc.data.challenges.leaderboardData

@Composable
fun ChallengesScreen(viewModel: AddChallengesViewModel) {
    var tabIndex by remember { mutableStateOf(0) }

    val tabs = listOf("Add Challenges", "Progress", "Leaderboard")

    Column(modifier = Modifier.fillMaxWidth()) {
        TabRow(selectedTabIndex = tabIndex) {
            tabs.forEachIndexed { index, title ->
                Tab(text = { Text(title) },
                    selected = tabIndex == index,
                    onClick = { tabIndex = index }
                )
            }
        }
        when (tabIndex) {
            0 -> AddChallengesTab(viewModel = viewModel)
            1 -> ChallengesProgressTab(
                challenges = viewModel.challenges.collectAsState(emptyList()).value,
                onStatusChange = { challenge, newStatus, marks ->
                    viewModel.updateChallengeStatusAndMarks(challenge, newStatus, marks)
                }
            )
            2 -> LeaderboardTab(leaderboardData = leaderboardData)
        }
    }
}

