package com.example.cliwatchjc

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.cliwatchjc.ui.theme.GreenAwarenessTheme

object Routes {
    const val MAIN_MENU = "mainMenu"
    const val EDUCATION = "education"
    const val TRACKER = "tracker"
    const val CHALLENGES = "challenges"

    val labels = mapOf(
        MAIN_MENU to "Main Menu",
        EDUCATION to "Education Module",
        TRACKER to "Tracker Module",
        CHALLENGES to "Challenges Module"
    )
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MyApp() {
    val navController = rememberNavController()

    GreenAwarenessTheme {
        Scaffold(
            bottomBar = {
                NavigationBar {
                    val navBackStackEntry by navController.currentBackStackEntryAsState()
                    val currentRoute = navBackStackEntry?.destination?.route

                    NavigationBarItem(
                        label = { Text(Routes.labels[Routes.MAIN_MENU] ?: "") },
                        icon = { /* Place any icon or placeholder here */ },
                        selected = currentRoute == Routes.MAIN_MENU,
                        onClick = {
                            navController.navigate(Routes.MAIN_MENU) {
                                launchSingleTop = true
                            }
                        }
                    )
                    NavigationBarItem(
                        label = { Text(Routes.labels[Routes.EDUCATION] ?: "") },
                        icon = { /* Placeholder */ },
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
                        selected = currentRoute == Routes.CHALLENGES,
                        onClick = {
                            navController.navigate(Routes.CHALLENGES) {
                                launchSingleTop = true
                            }
                        }
                    )
                }
            }
        ) {
            NavHost(navController, startDestination = Routes.MAIN_MENU) {
                composable(Routes.MAIN_MENU) { MainMenuScreen() }
                composable(Routes.EDUCATION) { EducationScreen() }
                composable(Routes.TRACKER) { TrackerScreen() }
                composable(Routes.CHALLENGES) { ChallengesScreen() }
            }
        }
    }
}

@Preview(showBackground = true, name = "MyApp Preview")
@Composable
fun MyAppPreview() {
    MyApp()
}
