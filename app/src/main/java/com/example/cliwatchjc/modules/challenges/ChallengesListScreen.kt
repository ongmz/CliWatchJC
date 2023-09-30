package com.example.cliwatchjc.modules.challenges

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.cliwatchjc.Routes
import com.example.cliwatchjc.data.challenges.Challenges
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext

@Composable
fun ChallengesListScreen(navController: NavController) {
    val challengesViewModel: ChallengesViewModel = hiltViewModel()

    val challenges = challengesViewModel.challenges.collectAsState().value


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 56.dp)
    ) {
        ChallengesList(challenges, challengesViewModel, navController)
    }
}

@Composable
fun ChallengesList(challenges: List<Challenges>, challengesViewModel: ChallengesViewModel, navController: NavController) {
    LazyColumn {
        items(challenges) { challenge -> // Use the singular "challenge" instead of "challenges"
            ChallengesItem(challenge, challengesViewModel, navController) // Pass "challenge" to ChallengesItem
        }
    }
}

@Composable
fun ChallengesItem(challenges: Challenges,
                   challengesViewModel:ChallengesViewModel,
                   navController: NavController) {
    val currentChallenge = rememberUpdatedState(challenges)
    val context = LocalContext.current
    var message by remember { mutableStateOf<String?>(null) }
    Card(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
            .clickable {
                navController.navigate("${Routes.CONTENT}/${challenges.challengesId}")
            },
        elevation = CardDefaults.cardElevation(defaultElevation = 10.dp),
        shape = RoundedCornerShape(4.dp)
    ) {
        Box(
            modifier = Modifier
                .height(140.dp)
                .fillMaxWidth()
        ) {
            // Display the article title and content
            Column(
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = challenges.title,
                    style = MaterialTheme.typography.titleLarge.copy(
                        fontWeight = FontWeight.Bold,
                        fontSize = 24.sp
                    ),
                    modifier = Modifier
                        .padding(top = 12.dp, start = 8.dp)
                )
                Text(
                    text = challenges.content,
                    fontSize = 12.sp,
                    modifier = Modifier
                        .padding(start = 8.dp)
                )
                Button(
                    onClick = {
                        // Handle taking the challenge here
                        val result = challengesViewModel.takeChallenge(challenges)
                        if (result) {
                            // Challenge added successfully
                            message = "Challenge added successfully"
                        } else {
                            // Challenge failed to add
                            message = "Failed to add challenge"
                        }
                    },
                    modifier = Modifier
                        .padding(16.dp)
                ) {
                    Text(text = "Take Challenge")
                }
            }

        }
        LaunchedEffect(message) {
            message?.let { msg ->
                Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
            }
        }
    }
}