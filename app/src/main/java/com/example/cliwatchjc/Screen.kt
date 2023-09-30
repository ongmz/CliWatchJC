package com.example.cliwatchjc

import ChallengesProgressTab
import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarDefaults.containerColor
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.cliwatchjc.data.challenges.Challenges
import com.example.cliwatchjc.modules.challenges.ChallengesContentScreen
import com.example.cliwatchjc.modules.challenges.ChallengesListScreen
import com.example.cliwatchjc.modules.challenges.ChallengesScreen
import com.example.cliwatchjc.modules.challenges.ChallengesViewModel
import com.example.cliwatchjc.modules.tracker.TrackerScreen
import com.example.compose.AppTheme

object Routes {
    const val MAIN_MENU = "mainMenu"
    const val EDUCATION = "education"
    const val TRACKER = "tracker"
    const val CHALLENGES = "challenges"
    const val CHALLENGES_LIST = "challengesList"
    const val CONTENT = "content"
    const val PROGRESS = "progress"

    val labels = mapOf(
        MAIN_MENU to "Main Menu",
        EDUCATION to "Education",
        TRACKER to "tracker",
        CHALLENGES to  "challenges",
        CHALLENGES_LIST to  "challengesList",
        CONTENT to "content",
        PROGRESS to "progress"
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MyApp() {
    val navController = rememberNavController()
    var showSideMenu by remember { mutableStateOf(false) }

    AppTheme {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("CliWatch") },
                    navigationIcon = {
                        IconButton(onClick = { showSideMenu = !showSideMenu }) {
                            Icon(Icons.Default.Menu, contentDescription = "Open Side Menu")
                        }
                    },
                    actions = {
                        IconButton(onClick = { /* TODO: Handle main menu click */ }) {
                            Icon(
                                Icons.Default.Home, contentDescription = "Main Menu",
                                modifier = Modifier
                                    .size(32.dp)
                            )
                        }
                    },
                    colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
                )
            },

            bottomBar = {
                NavigationBar {
                    val navBackStackEntry by navController.currentBackStackEntryAsState()
                    val currentRoute = navBackStackEntry?.destination?.route

                    NavigationBarItem(
                        label = { Text(Routes.labels[Routes.EDUCATION] ?: "", fontSize = 12.sp) },
                        icon = { Icon(painter = painterResource(R.drawable.ic_education), contentDescription = "", Modifier.size(32.dp)) },
                        selected = currentRoute == Routes.EDUCATION,
                        onClick = {
                            navController.navigate(Routes.EDUCATION) {
                                launchSingleTop = true
                            }
                        }
                    )
                    NavigationBarItem(
                        label = { Text(Routes.labels[Routes.TRACKER] ?: "") },
                        icon = { /* Placeholder */ },
                        selected = currentRoute == Routes.TRACKER,
                        onClick = {
                            navController.navigate(Routes.TRACKER) {
                                launchSingleTop = true
                            }
                        }
                    )

                    NavigationBarItem(
                        label = { Text(Routes.labels[Routes.CHALLENGES] ?: "") },
                        icon = { /* Placeholder */ },
                        selected = currentRoute == Routes.CHALLENGES ||
                                currentRoute == Routes.CHALLENGES_LIST ||
                                currentRoute?.startsWith(Routes.CONTENT) == true,
                        onClick = {
                            navController.navigate(Routes.CHALLENGES) {
                                launchSingleTop = true
                            }
                        }
                    )

                }
            }
        ) {
            Box(modifier = Modifier.fillMaxSize()) {
                NavHost(navController, startDestination = Routes.MAIN_MENU) {
                    composable(Routes.MAIN_MENU) { MainMenuScreen() }
                    composable(Routes.TRACKER) { TrackerScreen() }

                    composable(Routes.CHALLENGES) { ChallengesScreen(navController) }

                    composable(Routes.CHALLENGES_LIST) {
                        ChallengesListScreen(navController)
                    }

                    composable(
                        route = "${Routes.CONTENT}/{challengeId}")
                    { backStackEntry ->
                        val challengesIdSTring = backStackEntry.arguments?.getString("challengesId")
                        val challengesId = challengesIdSTring?.toLongOrNull() ?: 0L
                        ChallengesContentScreen(challengesId, navController)

                    }


                    composable(Routes.PROGRESS) {
                        // Assuming you have a list of challenges and an update function in your ViewModel
                        val challengesViewModel: ChallengesViewModel = hiltViewModel()
                        val selectedChallengeId by challengesViewModel.selectedChallengeId.collectAsState()
                        val challenges = challengesViewModel.challenges.collectAsState().value

                        // Define your updateChallengeStatus function here
                        val updateChallengeStatus: (Challenges, String) -> Unit = { challenge, status ->
                            // Define your logic to update the challenge status here
                            // This function should be provided by your ViewModel
                            challengesViewModel.updateChallengeStatus(challenge, status)
                        }

                        ChallengesProgressTab(selectedChallengeId, challenges, updateChallengeStatus)
                    }



                }

                if (showSideMenu) {
                    SideMenu(onClose = { showSideMenu = false })
                }
            }


        }
    }
}

@Composable
fun SideMenu(onClose: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxHeight()
            .width(250.dp)
            .background(Color.White)
            .clip(shape = RoundedCornerShape(0.dp, 8.dp, 8.dp, 0.dp))
    ) {
        Column {
            // Add items for your side menu here. For example:
            Text("Item 1", modifier = Modifier.padding(16.dp))
            Text("Item 2", modifier = Modifier.padding(16.dp))
            // ...

            Spacer(modifier = Modifier.weight(1f))

            Button(onClick = onClose, modifier = Modifier.align(Alignment.CenterHorizontally)) {
                Text("Close")
            }
        }
    }
}

@Preview(showBackground = true, name = "MyApp Preview")
@Composable
fun MyAppPreview() {
    MyApp()
}
