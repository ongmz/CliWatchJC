package com.example.cliwatchjc.modules.challenges

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun ChallengesScreen() {
//    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
//        Text(text = Routes.labels[Routes.CHALLENGES] ?: "")
//    }
    val navController = rememberNavController()
    val screens = listOf("Add Challenges", "Challenges Progress", "Leaderboard")
    var currentScreen by remember { mutableStateOf(screens.first()) }

    val lifecycleOwner = LocalLifecycleOwner.current
    val lifecylce1 = lifecycleOwner.lifecycle
    val lifecylce2 = lifecycleOwner.lifecycle
    val lifecylce3 = lifecycleOwner.lifecycle

    Scaffold(
        topBar = {
            TabRow(selectedTabIndex = screens.indexOf(currentScreen)){
                screens.forEachIndexed { index, s ->
                    Tab(
                        selected = index == screens.indexOf(currentScreen),
                        onClick = {
                            currentScreen = s
                            navController.navigate(s)
                        },
                        text = {
                            when(index){
                                0 -> Text("Add Challenges")
                                1 -> Text("Challenges Progress")
                                2 -> Text("Leaderboard")
                                else -> Text("Unknown")
                            }
                        }
                    )
                }
            }
        }
    ){ innerPadding ->
        NavHost(
            navController = navController,
            startDestination = screens.first(),
            Modifier.padding(innerPadding)
        ) {
            composable(route = "Add Challenges") {
                Screen1(lifecylce1)
            }
            composable(route = "Challenges Progress") {
                Screen2(lifecylce2)
            }
            composable(route = "Leaderboard") {
                Screen3(lifecylce3)
            }
        }
    }

}