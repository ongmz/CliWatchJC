package com.example.cliwatchjc.modules.challenges

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.cliwatchjc.data.challenges.leaderboardData

@Composable
fun ChallengesScreen(viewModel: AddChallengesViewModel) {
    var selectedTabIndex by remember { mutableIntStateOf(0) }


    Column (
        modifier = Modifier
            .padding(top = 60.dp)
            .fillMaxSize()
            .background(Color.White)
    ){
        TabRow(
            selectedTabIndex = selectedTabIndex,
            contentColor = Color.Black
        ) {
            Tab(
                text = { Text("Add Challenges") },
                selected = selectedTabIndex == 0,
                onClick = { selectedTabIndex = 0 }
            )
            Tab(
                text = { Text("Progress") },
                selected = selectedTabIndex == 1,
                onClick = { selectedTabIndex = 1 }
            )
            Tab(
                text = { Text("Leaderboard") },
                selected = selectedTabIndex == 2,
                onClick = { selectedTabIndex = 2 }
            )
        }

        when (selectedTabIndex) {
            0 -> AddChallengesTab(viewModel=viewModel)
            1 -> ChallengesProgressTab(
                challenges = viewModel.challenges.collectAsState(emptyList()).value,
                onStatusChange = { challenge, newStatus, marks ->
                    viewModel.updateChallengeStatusAndMarks(challenge, newStatus, marks)
                }
            )
            2 ->  LeaderboardTab(leaderboardData = leaderboardData) // Implement your Leaderboard tab Composable
        }
    }
}
