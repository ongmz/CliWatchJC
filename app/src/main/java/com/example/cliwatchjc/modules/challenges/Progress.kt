package com.example.cliwatchjc.modules.challenges

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cliwatchjc.data.challenges.Challenges



@Composable
fun ChallengesProgressTab(
    challenges: List<Challenges>, // List of added challenges
    updateChallengeStatus: (Challenges, String) -> Unit // Function to update challenge status
) {
    LazyColumn {
        items(challenges) { challenge ->
            ChallengeDetailItem(challenge, updateChallengeStatus)
        }
    }
}

@Composable
fun ChallengeDetailItem(
    challenge: Challenges,
    updateChallengeStatus: (Challenges, String) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 10.dp),
        shape = RoundedCornerShape(4.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
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
                        updateChallengeStatus(challenge.copy(completed = !challenge.completed), newStatus)
                    },
                    modifier = Modifier.height(40.dp)
                ) {
                    Text(text = buttonLabel(challenge))
                }
            }
        }
    }
}


// Function to check if a challenge is already in progress

// Function to get the challenge status text based on its completion status
private fun challengeStatusText(challenge: Challenges): String {
    return if (challenge.completed) "Completed" else "On-going"
}

// Function to get the color for the challenge status text
private fun challengeStatusColor(challenge: Challenges): Color {
    return if (challenge.completed) Color.Green else Color.Red
}

// Function to toggle the challenge completion status
private fun toggleChallengeStatus(challenge: Challenges): String {
    return if (challenge.completed) "on-going" else "completed"
}

// Function to get the label for the button based on challenge completion status
private fun buttonLabel(challenge: Challenges): String {
    return if (challenge.completed) "Mark as On-going" else "Mark as Completed"
}



