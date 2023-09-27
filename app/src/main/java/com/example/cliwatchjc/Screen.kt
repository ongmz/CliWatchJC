package com.example.cliwatchjc

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Button
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.cliwatchjc.data.UserViewModel
import com.example.cliwatchjc.modules.challenges.ChallengesScreen
import com.example.cliwatchjc.modules.education.ArticleContentScreen
import com.example.cliwatchjc.modules.education.ClimateNewsScreen
import com.example.cliwatchjc.modules.education.ArticleListScreen
import com.example.cliwatchjc.modules.education.EducationScreen
import com.example.cliwatchjc.modules.education.QuizComplete
import com.example.cliwatchjc.modules.education.QuizScreen
import com.example.cliwatchjc.modules.education.WebViewScreen
import com.example.cliwatchjc.modules.tracker.TrackerScreen
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import com.example.cliwatchjc.modules.tracker.CalculatorScreen
import com.example.cliwatchjc.modules.tracker.CalculatorViewModel
import com.example.cliwatchjc.modules.tracker.PersonalGoalScreen
import com.example.cliwatchjc.modules.tracker.PersonalGoalViewModel
import com.example.cliwatchjc.modules.tracker.SummaryScreen
import com.example.compose.AppTheme

object Routes {
    const val MAIN_MENU = "mainMenu"
    const val EDUCATION = "education"
    const val EDUCATION_RESOURCES = "educationResources"
    const val ARTICLE_CONTENT = "articleContent"
    const val QUIZ = "quizScreen"
    const val QUIZ_COMPLETE = "quizComplete/{score}/{totalQuestions}"
    const val CLIMATE_NEWS = "climateNews"
    const val CLIMATE_NEWS_CONTENT = "webViewScreen?url={url}"
    const val TRACKER = "tracker"
    const val CALCULATOR = "calculator"
    const val PERSONAL_GOAL = "personalGoal"
    const val SUMMARY = "summary"
    const val CHALLENGES = "challenges"

    val labels = mapOf(
        MAIN_MENU to "Main Menu",
        EDUCATION to "Education",
        EDUCATION_RESOURCES to "Education Resources",
        ARTICLE_CONTENT to "Article Content",
        QUIZ to "Quiz",
        QUIZ_COMPLETE to "quizComplete",
        CLIMATE_NEWS to "Climate News",
        TRACKER to "Tracker",
        CALCULATOR to "Calculator",
        PERSONAL_GOAL to "Personal Goal",
        SUMMARY to "Summary",
        CHALLENGES to "Challenges"
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MyApp() {
    val navController = rememberNavController()
    val coroutineScope: CoroutineScope = rememberCoroutineScope()
    val drawerState: DrawerState = rememberDrawerState(initialValue = DrawerValue.Closed)

    val userViewModel: UserViewModel = hiltViewModel()
    userViewModel.createUser()

    ModalNavigationDrawer(drawerContent = {
        AppDrawer(
            closeDrawer = { coroutineScope.launch { drawerState.close() } }
        )
    }, drawerState = drawerState) {
        Scaffold(
            topBar = {
                val scope = rememberCoroutineScope()
                TopAppBar(
                    title = { Text("CliWatch", fontSize = 24.sp) },
                    navigationIcon = {
                        IconButton(onClick = { scope.launch { drawerState.open() } }) {
                            Icon(Icons.Default.Menu, contentDescription = "Open Side Menu")
                        }
                    },
                    actions = {
                        IconButton(onClick = { navController.navigate(Routes.MAIN_MENU) }) {
                            Icon(
                                    Icons.Default.Home, contentDescription = "Main Menu",
                                    modifier = Modifier
                                            .size(32.dp)
                            )
                        }
                    },
                    colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.primaryContainer),
                )
            },
                bottomBar = {
                    NavigationBar {
                        val navBackStackEntry by navController.currentBackStackEntryAsState()
                        val currentRoute = navBackStackEntry?.destination?.route

                        NavigationBarItem(
                            label = { Text(Routes.labels[Routes.EDUCATION] ?: "") },
                            icon = {
                                Icon(
                                    painter = painterResource(R.drawable.ic_education),
                                    contentDescription = ""
                                )
                            },
                            selected = currentRoute == Routes.EDUCATION ||
                                currentRoute == Routes.EDUCATION_RESOURCES ||
                                currentRoute?.startsWith(Routes.ARTICLE_CONTENT) == true ||
                                currentRoute == Routes.QUIZ ||
                                currentRoute == Routes.QUIZ_COMPLETE ||
                                currentRoute == Routes.CLIMATE_NEWS_CONTENT ||
                                currentRoute == Routes.CLIMATE_NEWS,
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
        )
        {
            Box(modifier = Modifier.fillMaxSize()) {
                NavHost(navController, startDestination = Routes.MAIN_MENU) {
                    composable(Routes.MAIN_MENU) { MainMenuScreen() }
                    composable(Routes.EDUCATION) { EducationScreen(navController) }
                    composable(Routes.EDUCATION_RESOURCES) { ArticleListScreen(navController) }
                    composable("${Routes.ARTICLE_CONTENT}/{articleId}") { backStackEntry ->
                        val articleIdString = backStackEntry.arguments?.getString("articleId")
                        val articleId = articleIdString?.toLongOrNull() ?: 0L
                        ArticleContentScreen(articleId, navController)
                    }
                    composable("${Routes.QUIZ}/{articleId}") { backStackEntry ->
                        val articleIdString = backStackEntry.arguments?.getString("articleId")
                        val articleId = articleIdString?.toLongOrNull() ?: 0L
                        QuizScreen(articleId, navController)
                    }
                    composable(
                        route = Routes.QUIZ_COMPLETE,
                        arguments = listOf(
                            navArgument("score") {
                                type = NavType.IntType
                            },
                            navArgument("totalQuestions") {
                                type = NavType.IntType
                            }
                        )
                    ) { backStackEntry ->
                        val scoreArg = backStackEntry.arguments?.getInt("score") ?: -1
                        val totalQuestionsArg = backStackEntry.arguments?.getInt("totalQuestions") ?: -1
                        QuizComplete(scoreArg, totalQuestionsArg, navController)
                    }
                    composable(Routes.CLIMATE_NEWS) { ClimateNewsScreen(navController) }
                    composable(Routes.CLIMATE_NEWS_CONTENT) { backStackEntry ->
                        val urlArg = backStackEntry.arguments?.getString("url")
                        if (urlArg != null) {
                            WebViewScreen(urlArg)
                        } else {
                            // Handle error, maybe pop back or show an error screen
                        }
                    }
                    composable(Routes.TRACKER) { TrackerScreen(navController) }
                    composable(Routes.CALCULATOR) { CalculatorScreen(calculatorViewModel = CalculatorViewModel()) }
                    composable(Routes.PERSONAL_GOAL) { PersonalGoalScreen() }
                    composable(Routes.SUMMARY) { SummaryScreen()}
                    composable(Routes.CHALLENGES) { ChallengesScreen() }
                }
            }
        }
    }
}

@Composable
fun AppDrawer(closeDrawer: () -> Unit = {}) {
    ModalDrawerSheet {
        LazyColumn(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
        ) {
            item { DrawerHeader() }
            item { Spacer(modifier = Modifier.height(8.dp)) }
            item {
                Text(
                    text = "About Us",
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                        .wrapContentSize(Alignment.Center)
                )
            }

            item {
                Text(
                    text = "CliWatch is your companion in the fight against climate change. We provide education, news, and tools to help you reduce your carbon footprint and make a positive impact on our planet.",
                    textAlign = TextAlign.Justify,
                    fontSize = 16.sp,
                    letterSpacing = 0.1.sp,
                    modifier = Modifier.padding(horizontal = 12.dp)
                    )
            }

            item { Spacer(modifier = Modifier.height(20.dp)) }

            item {
                Text(
                    text = "We are group of students from TARUMT, presenting our assignment of developing mobile application using Android Studio.",
                    textAlign = TextAlign.Justify,
                    fontSize = 16.sp,
                    modifier = Modifier.align(Alignment.CenterHorizontally).padding(horizontal = 12.dp)
                )
            }

            item { Spacer(modifier = Modifier.height(16.dp)) }

            item {
                Column {
                    Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.padding(horizontal = 40.dp, vertical = 16.dp).fillMaxWidth()) {
                        Column {
                            Image(
                                painterResource(id = R.drawable.img_leon),
                                contentDescription = "",
                                modifier = Modifier
                                    .size(96.dp)
                                    .clip(CircleShape)
                                    .border(2.dp, Color.Gray, CircleShape)
                            )
                            Text(
                                text = "Ong Ming Zheng",
                                fontSize = 12.sp,
                                modifier = Modifier
                                    .align(Alignment.CenterHorizontally)
                                    .padding(top = 8.dp)
                            )
                        }
                        Column {
                            Image(
                                painterResource(id = R.drawable.img_leon),
                                contentDescription = "",
                                modifier = Modifier
                                    .size(96.dp)
                                    .clip(CircleShape)
                                    .border(2.dp, Color.Gray, CircleShape)
                            )
                            Text(
                                text = "Ong Ming Zheng",
                                fontSize = 12.sp,
                                modifier = Modifier
                                    .align(Alignment.CenterHorizontally)
                                    .padding(top = 8.dp)
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(32.dp))

                    Row(
                        modifier = Modifier.align(Alignment.CenterHorizontally).padding(top = 8.dp)) {
                        Column {
                            Image(
                                painterResource(id = R.drawable.img_leon),
                                contentDescription = "",
                                modifier = Modifier
                                    .size(96.dp)
                                    .clip(CircleShape)
                                    .border(2.dp, Color.Gray, CircleShape)
                            )
                            Text(
                                text = "Ong Ming Zheng",
                                fontSize = 12.sp,
                                modifier = Modifier
                                    .align(Alignment.CenterHorizontally)
                                    .padding(top = 8.dp)
                            )
                        }
                    }
                }
                Spacer(modifier = Modifier.height(32.dp))
            }
        }
    }
}

@Composable
fun DrawerHeader() {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.Start,
        modifier = Modifier
                .background(MaterialTheme.colorScheme.secondary)
                .fillMaxWidth()
    ) {
        Image(
            painterResource(id = R.drawable.img_drawer_header),
            contentDescription = ""
        )
    }
}

@Preview
@Composable
fun DrawerHeaderPreview() {
    AppDrawer()
}



