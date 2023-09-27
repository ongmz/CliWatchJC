package com.example.cliwatchjc.modules.challenges

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.cliwatchjc.data.challenges.AddChallenges


@Composable
fun AddChallengesTab(viewModel: AddChallengesViewModel) {
    val challenges = viewModel.challenges.collectAsState(emptyList()).value

    LazyColumn(
        modifier = Modifier.padding(16.dp)
    ) {
        items(challenges.size) { index ->
            val challenge = challenges[index]
            ChallengeCard(challenge = challenge) {
                // Call the provided callback when the "Add Challenge" button is clicked
                viewModel.insertChallenge(challenge)
            }
        }
    }
}

@Composable
fun ChallengeCard(challenge: AddChallenges, onAddClicked: () -> Unit) {
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

            Button(
                onClick = {
                    // Call the provided callback when the "Add Challenge" button is clicked
                    onAddClicked()
                },
                modifier = Modifier
                    .align(Alignment.End)
                    .padding(top = 8.dp)
            ) {
                Text(text = "Add Challenge")
            }
        }
    }
}
//@Preview
//@Composable
//fun SummaryPreview() {
//    AddChallengesTab()
//}
