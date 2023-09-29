package com.example.cliwatchjc.modules.challenges

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.cliwatchjc.data.challenges.AddChallenges


@Composable
fun ChallengesProgressTab(
    sharedViewModel: SharedChallengesViewModel,
    onStatusChange: (AddChallenges, String, Int) -> Unit
) {
    val challenges = sharedViewModel.challenges // Collect challenges from the shared ViewModel

    LazyColumn(
        modifier = Modifier.padding(16.dp)
    ) {
        items(challenges.size) { index ->
            val challenge = challenges[index]
            ChallengeProgressCard(challenge = challenge) { newStatus, marks ->
                onStatusChange(challenge, newStatus, marks)
            }
        }
    }
}

@Composable
fun ChallengeProgressCard(
    challenge: AddChallenges,
    onStatusChange: (String, Int) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            Text(text = challenge.challenges_title, style = TextStyle(fontWeight = FontWeight.Bold))
            Text(text = challenge.challenges_desc, modifier = Modifier.padding(top = 4.dp))
            Text(text = "Duration: ${challenge.challenges_duration}", modifier = Modifier.padding(top = 4.dp))

            // Input field for marks
            var marks by remember { mutableStateOf(challenge.marks) }
            TextField(
                value = marks.toString(),
                onValueChange = {
                    marks = it.toIntOrNull() ?: 0
                },
                label = { Text("Marks") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp)
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Button(
                    onClick = {
                        // Call the provided callback with "Completed" status and marks
                        onStatusChange("Completed", marks)
                    }
                ) {
                    Text(text = "Complete")
                }

                Button(
                    onClick = {
                        // Call the provided callback with "Ongoing" status and marks
                        onStatusChange("Ongoing", marks)
                    }
                ) {
                    Text(text = "Ongoing")
                }
            }
        }
    }
}


