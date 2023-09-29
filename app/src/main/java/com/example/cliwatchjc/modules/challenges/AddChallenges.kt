package com.example.cliwatchjc.modules.challenges

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.cliwatchjc.data.challenges.AddChallenges
import kotlinx.coroutines.delay


@Composable
fun AddChallengesTab(viewModel: AddChallengesViewModel) {
    val challenges = viewModel.challenges.collectAsState(emptyList()).value
    var toastMessage by remember { mutableStateOf<Pair<String, Color>?>(null) }

    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
            .verticalScroll(scrollState) // Enable vertical scroll
    ) {
        challenges.forEach { challenge ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp) // Add vertical spacing between cards
            ) {
                Column(
                    modifier = Modifier
                        .padding(start = 16.dp, top = 16.dp, end = 16.dp)
                        .fillMaxWidth(),
                ) {
                    Text(text = challenge.challenges_title, style = TextStyle(fontWeight = FontWeight.Bold))
                    Text(text = challenge.challenges_desc, modifier = Modifier.padding(top = 4.dp))
                    Text(text = "Duration: ${challenge.challenges_duration}", modifier = Modifier.padding(top = 4.dp))

                    Button(
                        onClick = {
                            viewModel.insertChallenge(challenge)
                            // You can handle the result in the ViewModel's function
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
    }

    // Display toast message if not null
    toastMessage?.let { (message, color) ->
        ToastMessage(message = message, color = color)
    }
}

@Composable
fun ToastMessage(message: String, color: Color) {
    val currentMessage by rememberUpdatedState(message)
    var isShowing by remember { mutableStateOf(true) }

    LaunchedEffect(currentMessage) {
        delay(2000) // Display the message for 2 seconds (adjust as needed)
        isShowing = false
    }

    if (isShowing) {
        SnackbarHost(
            modifier = Modifier.background(color), // Background color
            hostState = remember { SnackbarHostState() }
        ) {
            Snackbar(
                modifier = Modifier.padding(8.dp),
                content = { Text(text = currentMessage, color = Color.White) }
            )
        }
    }
}
