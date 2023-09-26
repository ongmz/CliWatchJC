package com.example.cliwatchjc.modules.challenges

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.cliwatchjc.data.challenges.AddChallenges


@Composable
fun Screen1(lifecycle: Lifecycle){
    val viewModel: ChallengesViewModel = viewModel()
    val cardData = viewModel.cardData.collectAsState(emptyList())
    val selectedChallenge = remember{ mutableStateOf<AddChallenges?>(null) }


    val onResumeEventObserver = remember(lifecycle){
        object : LifecycleEventObserver{
            override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
                if(event == Lifecycle.Event.ON_RESUME){

                    println("onResume Screen1")
                }else if (event == Lifecycle.Event.ON_PAUSE){
                    println("onPause Screen1")
                }else if (event == Lifecycle.Event.ON_CREATE){
                    println("onCreate Screen1")
                }else if (event == Lifecycle.Event.ON_START){
                    println("onStart Screen1")
                }else if (event == Lifecycle.Event.ON_STOP){
                    println("onStop Screen1")
                }else if (event == Lifecycle.Event.ON_DESTROY){
                    println("onDestroy Screen1")
                }
            }
        }
    }
    DisposableEffect(lifecycle){
        lifecycle.addObserver(onResumeEventObserver)
        onDispose { lifecycle.removeObserver(onResumeEventObserver) }
    }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ){
        Spacer(modifier = Modifier.height(16.dp))

        cardData.value.forEach { cardData ->
            Card (
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp)
            ) {
                Column(
                    modifier = Modifier
                        .padding(16.dp)
                ) {
                    Text(
                        text = cardData.challenges_title,
                        style = MaterialTheme.typography.titleMedium
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = cardData.challenges_duration,
                        style = MaterialTheme.typography.bodyMedium
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "number of participants",
                        style = MaterialTheme.typography.bodySmall
                    )
                }
            }
        }
//        Image(
//            painter = painterResource(R.drawable.placeholder),
//            contentDescription = null,
//            modifier = Modifier
//                .size(120.dp)
//                .clip(shape = CircleShape)
        selectedChallenge.value?.let { challenge ->
            ChallengeDescriptionScreen(
                challengeData = challenge,
                onCloseClick = {
                    // Close button clicked
                    selectedChallenge.value = null
                }
            )
        }

    }
}

@Composable
fun ChallengeDescriptionScreen(
    challengeData: AddChallenges, // Pass the selected challenge data here
    onCloseClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Text(
                text = challengeData.challenges_title,
                style = MaterialTheme.typography.titleMedium
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = challengeData.challenges_desc,
                style = MaterialTheme.typography.bodyMedium
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = challengeData.challenges_duration,
                style = MaterialTheme.typography.bodyMedium
            )
            Spacer(modifier = Modifier.height(16.dp))
            // Close button
            Button(
                onClick = { onCloseClick() },
                modifier = Modifier
                    .align(Alignment.End)
            ) {
                Text(text = "Close")
            }
        }
    }
}
