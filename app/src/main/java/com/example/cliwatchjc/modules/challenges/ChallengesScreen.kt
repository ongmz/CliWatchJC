package com.example.cliwatchjc.modules.challenges

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.*
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.example.cliwatchjc.data.challenges.Leaderboard
import com.example.cliwatchjc.data.challenges.adminChallenges

@Composable
fun ChallengesScreen() {
    var selectedTabIndex by remember { mutableIntStateOf(0) }

    val screens = listOf("Add Challenges", "Challenges Progress", "Leaderboard")

    Column (
        modifier = Modifier
            .padding(top = 60.dp)
            .fillMaxSize()
            .background(Color.White)
    ){
        TabRow(
            selectedTabIndex =selectedTabIndex,
            contentColor = Color.Black
        ) {
            Tab(
                text = { Text("Add Challenges") },
                selected = selectedTabIndex in 0..2,
                onClick = { selectedTabIndex = 0 }
            )
            Tab(
                text = { Text("Progress") },
                selected = selectedTabIndex in 0..2,
                onClick = { selectedTabIndex = 0 }
            )
            Tab(
                text = { Text("Leaderboard") },
                selected = selectedTabIndex in 0..2,
                onClick = { selectedTabIndex = 0 }
            )

        }
        when (selectedTabIndex) {
            0 -> AddChallengesTab()
            1 -> ChallengesProgressTab()
            2 -> LeaderboardTab() // Implement your Leaderboard tab Composable
        }
    }
}
