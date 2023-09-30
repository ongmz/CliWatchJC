package com.example.cliwatchjc.modules.challenges


import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.*
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.cliwatchjc.data.challenges.Challenges

@Composable
fun ChallengesProgressTab(
    selectedChallengeId: Long?,
    challenges: List<Challenges>,
    updateChallengeStatus: (Challenges, String) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 16.dp, end = 16.dp)
    ) {
        Spacer(modifier = Modifier.height(80.dp))
        challenges.forEach { challenge ->
            ChallengeCard(challenge, selectedChallengeId, updateChallengeStatus)
            Spacer(modifier = Modifier.height(20.dp).background(Color.Gray))
        }
    }
}

@Composable
fun ProgressScreen(challengesViewModel: ChallengesViewModel, navController: NavController) {
    val selectedChallengeId by challengesViewModel.selectedChallengeId.collectAsState()
    val challenges = challengesViewModel.challenges.collectAsState().value

    ChallengesProgressTab(selectedChallengeId, challenges) { challenge, status ->
        challengesViewModel.updateChallengeStatus(challenge, status)
    }
}




@Composable
fun ChallengeCard(
    challenge: Challenges,
    selectedChallengeId: Long?,
    updateChallengeStatus: (Challenges, String) -> Unit
) {
    var isButtonClicked by remember { mutableStateOf(false) }
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(180.dp)
            .clickable { /* Handle click, if needed */ },
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxSize()
        ) {
            Text(
                text = challenge.title,
                style = MaterialTheme.typography.titleLarge.copy(
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp
                ),
                modifier = Modifier.padding(bottom = 8.dp)
            )
            Text(
                text = challenge.content,
                fontSize = 16.sp,
                modifier = Modifier.padding(bottom = 16.dp)
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = challengeStatusText(challenge),
                    color = challengeStatusColor(challenge),
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
                Button(
                    onClick = {
                        val newStatus = toggleChallengeStatus(challenge)
                        updateChallengeStatus(challenge, newStatus) // Updated to pass newStatus as String
                        isButtonClicked = true

                    },
                    modifier = Modifier, // Adjust padding as needed
                    enabled = !isButtonClicked
                ) {
                    Text(text = buttonLabel(challenge))
                }
            }
        }
    }
}
private fun challengeStatusText(challenge: Challenges): String {
    return if (challenge.completed == "completed") "Completed" else "On-going"
}

// Function to get the color for the challenge status text
private fun challengeStatusColor(challenge: Challenges): Color {
    return if (challenge.completed == "completed") Color.Green else Color.Red
}

// Function to toggle the challenge completion status
private fun toggleChallengeStatus(challenge: Challenges): String {
    return if (challenge.completed == "completed") "on-going" else "completed"
}

// Function to get the label for the button based on challenge completion status
private fun buttonLabel(challenge: Challenges): String {
    return if (challenge.completed == "completed") "Mark as On-going" else "Mark as Completed"
}


