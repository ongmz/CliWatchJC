package com.example.cliwatchjc.modules.challenges

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController

sealed class ChallengesContentComponent {
    data class Paragraph(val text: String) : ChallengesContentComponent()
    data class BulletPoint(val text: String) : ChallengesContentComponent()
    data class Header(val text: String) : ChallengesContentComponent()
    // ... other types like headers, images, quotes, etc.
}

@Composable
fun ChallengesContentScreen(challengesId: Long, navController: NavController) {
    val challengesViewModel: ChallengesViewModel = hiltViewModel()
    val challenges = challengesViewModel.getChallenge(challengesId)
    val contentComponents = challengesViewModel.getChallengeContent(challengesId)

    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(start = 16.dp, top = 16.dp, end = 16.dp)
    ) {
        Spacer(modifier = Modifier.height(56.dp))

        if (challenges != null) {
            Text(
                text = challenges.title,
                style = MaterialTheme.typography.titleLarge
            )
            Divider(color = Color.Gray, thickness = 4.dp, modifier = Modifier.padding(vertical = 8.dp))

            // Display the article content components
            contentComponents?.forEach { component ->
                when (component) {
                    is ChallengesContentComponent.Paragraph -> Text(
                        text = component.text,
                        style = MaterialTheme.typography.bodySmall,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )

                    is ChallengesContentComponent.BulletPoint -> Text(
                        text = "\u2022 ${component.text}",
                        style = MaterialTheme.typography.bodySmall,
                        modifier = Modifier.padding(bottom = 8.dp, start = 8.dp)
                    )
                    is ChallengesContentComponent.Header -> {
                        Text(
                            text = component.text,
                            style = MaterialTheme.typography.headlineSmall,
                            modifier = Modifier.padding(bottom = 4.dp)
                        )
                        Divider(color = Color.Gray, thickness = 1.dp, modifier = Modifier.padding(bottom = 4.dp))
                    }
                    // TODO : Image
                }
            }

        } else {
            Text(
                text = "Article not found.",
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(bottom = 8.dp)
            )
        }

        Spacer(modifier = Modifier.height(80.dp))
    }
}