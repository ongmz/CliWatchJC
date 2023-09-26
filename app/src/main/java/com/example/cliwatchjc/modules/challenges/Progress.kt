package com.example.cliwatchjc.modules.challenges

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import java.util.Date


@Composable
fun Screen2(
    lifecycle: Lifecycle,
    userId: Long,
    challengeId: Long,
    title: String,
    description: String,
    duration: String,
    challengeStatus: String
) {
    var challengeCompletionData by remember { mutableStateOf<Map<Date, Boolean>>(emptyMap()) }
    val viewModel: ChallengesViewModel = viewModel()
    val cardData = viewModel.cardData.collectAsState(emptyList())

    val onResumeEventObserver = remember(lifecycle) {
        object : LifecycleEventObserver {
            override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
                if(event == Lifecycle.Event.ON_RESUME){

                    println("onResume Screen2")
                }else if (event == Lifecycle.Event.ON_PAUSE){
                    println("onPause Screen2")
                }else if (event == Lifecycle.Event.ON_CREATE){
                    println("onCreate Screen2")
                }else if (event == Lifecycle.Event.ON_START){
                    println("onStart Screen2")
                }else if (event == Lifecycle.Event.ON_STOP){
                    println("onStop Screen2")
                }else if (event == Lifecycle.Event.ON_DESTROY){
                    println("onDestroy Screen2")
                }
            }
        }
    }

    DisposableEffect(lifecycle) {
        lifecycle.addObserver(onResumeEventObserver)
        onDispose { lifecycle.removeObserver(onResumeEventObserver) }
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {
        Spacer(modifier = Modifier.height(16.dp))

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(120.dp)
        ) {
            Column(
                modifier = Modifier
                    .padding(16.dp)
            ) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleMedium
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = description,
                    style = MaterialTheme.typography.bodyMedium
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = duration,
                    style = MaterialTheme.typography.bodyMedium
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = challengeStatus, // Display challenge status here
                    style = MaterialTheme.typography.bodySmall
                )

                Button(
                    onClick = {
                        val newStatus = if (challengeStatus == "ongoing") "completed" else "ongoing"
                        viewModel.setChallengeStatus(challengeId, newStatus) // Pass challengeId as Long
                    },
                    modifier = Modifier
                        .align(Alignment.End)
                ) {
                    Text(text = "Complete")
                }
            }
        }

        ChallengeCalendar(
            challengeCompletionData = challengeCompletionData,
            onDateClicked = { date ->
                val mutableCompletionData = challengeCompletionData?.toMutableMap() ?: mutableMapOf()
                val currentStatus = mutableCompletionData[date]
                mutableCompletionData[date] = currentStatus?.not() ?: true
                challengeCompletionData = mutableCompletionData
            }
        )
    }
}