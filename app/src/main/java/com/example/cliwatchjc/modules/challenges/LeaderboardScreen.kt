package com.example.cliwatchjc.modules.challenges

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import com.example.cliwatchjc.data.challenges.LeaderboardEntity

@Composable
fun Screen3(lifecycle: Lifecycle,
            leaderboardEntries: List<LeaderboardEntity>,
            score: Int, // Pass the score as a parameter
            username: String, // Pass the username as a parameter
     ) {
    val onResumeEventObserver = remember(lifecycle) {
        object : LifecycleEventObserver {
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
        Text(text = "Your Score: $score")
        Text(text = "Your Username: $username")

        LazyRow {
            itemsIndexed(leaderboardEntries) { index, entry ->
                Card(
                    modifier = Modifier
                        .width(200.dp) // Adjust the width as needed
                        .height(120.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .padding(16.dp),
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(text = "UserID: ${entry.userId}")
                        Text(text = "Name: ${entry.username}")
                        Text(text = "Score: ${entry.score}")
                    }
                }

                if (index < leaderboardEntries.size - 1) {
                    Spacer(modifier = Modifier.width(16.dp)) // Add spacing between cards
                }
            }
        }

    }
}
