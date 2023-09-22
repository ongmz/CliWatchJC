package com.example.cliwatchjc

import android.annotation.SuppressLint
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
import com.example.cliwatchjc.modules.challenges.ChallengesScreen
import com.example.cliwatchjc.modules.education.ClimateNewsScreen
import com.example.cliwatchjc.modules.education.EducationResourcesScreen
import com.example.cliwatchjc.modules.education.EducationScreen
import com.example.cliwatchjc.modules.tracker.TrackerScreen
import com.example.cliwatchjc.modules.tracker.CalculatorScreen
import com.example.cliwatchjc.modules.tracker.PersonalGoalScreen
import com.example.cliwatchjc.modules.tracker.SummaryScreen
import com.example.cliwatchjc.ui.theme.GreenAwarenessTheme

object Routes {
    const val MAIN_MENU = "mainMenu"
    const val EDUCATION = "education"
    const val EDUCATION_RESOURCES = "educationResources"
    const val CLIMATE_NEWS = "climateNews"
    const val TRACKER = "tracker"
    const val CALCULATOR = "calculator"
    const val PERSONAL_GOAL = "personalGoal"
    const val SUMMARY = "summary"
    const val CHALLENGES = "challenges"

    val labels = mapOf(
        MAIN_MENU to "Main Menu",
        EDUCATION to "Education Module",
        EDUCATION_RESOURCES to "Education Resources",
        CLIMATE_NEWS to "Climate News",
        TRACKER to "Tracker Module",
        CALCULATOR to "Calculator",
        PERSONAL_GOAL to "Personal Goal",
        SUMMARY to "Summary",
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
                composable(Routes.EDUCATION) { EducationScreen(navController) }
                composable(Routes.EDUCATION_RESOURCES) { EducationResourcesScreen() }
                composable(Routes.CLIMATE_NEWS) { ClimateNewsScreen() }
                composable(Routes.TRACKER) { TrackerScreen() }
                composable(Routes.CALCULATOR) { CalculatorScreen() }
                composable(Routes.PERSONAL_GOAL) { PersonalGoalScreen() }
                composable(Routes.SUMMARY) { SummaryScreen() }
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
