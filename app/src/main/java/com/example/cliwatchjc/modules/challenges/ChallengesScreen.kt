package com.example.cliwatchjc.modules.challenges

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.cliwatchjc.data.challenges.LeaderboardEntity
import com.example.compose.AppTheme

@Composable
fun ChallengesScreen() {
    val navController = rememberNavController()
    val screens = listOf("Add Challenges", "Challenges Progress", "Leaderboard")
    var currentScreen by remember { mutableStateOf(screens.first()) }

    val lifecycleOwner = LocalLifecycleOwner.current
    val lifecycle1 = lifecycleOwner.lifecycle
    val lifecycle2 = lifecycleOwner.lifecycle
    val lifecycle3 = lifecycleOwner.lifecycle

    Scaffold(
        topBar = {
            TabRow(selectedTabIndex = screens.indexOf(currentScreen)) {
                screens.forEachIndexed { index, s ->
                    Tab(
                        selected = index == screens.indexOf(currentScreen),
                        onClick = {
                            currentScreen = s
                            navController.navigate(s)
                        },
                        text = {
                            when (index) {
                                0 -> Text("Add Challenges")
                                1 -> Text("Challenges Progress")
                                2 -> Text("Leaderboard")
                                else -> Text("Unknown")
                            }
                        }
                    )
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = screens.first(),
            Modifier.padding(innerPadding)
        ) {
            composable(route = "Add Challenges") {
                Screen1(lifecycle1)
            }
            composable(route = "Challenges Progress") {
                val userId = 1L // Replace with your actual userId
                val challengesId = 1L // Replace with your actual challengesId
                val title = "Challenge Title" // Replace with the actual title
                val description = "Challenge Description" // Replace with the actual description
                val duration = "Challenge Duration" // Replace with the actual duration
                val challengeStatus = "ongoing" // Replace with the actual status
                Screen2(
                    lifecycle2,
                    userId,
                    challengesId,
                    title,
                    description,
                    duration,
                    challengeStatus
                )
            }
            composable(route = "Leaderboard") {
                val leaderboardEntries = getSampleLeaderboardEntries()
                val score = 0 // Provide the default score value
                val username = "YourUsername" // Provide the default username
                Screen3(lifecycle3, leaderboardEntries, score, username)
            }

        }
    }
}


@Composable
fun getSampleLeaderboardEntries(): List<LeaderboardEntity> {
    return listOf(
        LeaderboardEntity(userId = 1, challengesId = 1, username = "User1", score = 100),
        LeaderboardEntity(userId = 2, challengesId = 1, username = "User2", score = 90)
    )
}

@Preview(showBackground = true)
@Composable
fun ScreenPreview() {
    AppTheme {
        ChallengesScreen()
    }
}