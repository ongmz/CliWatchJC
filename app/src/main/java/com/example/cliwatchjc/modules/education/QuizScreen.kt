package com.example.cliwatchjc.modules.education

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.os.bundleOf
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.cliwatchjc.Routes
import com.example.cliwatchjc.data.AppDatabase
import com.example.cliwatchjc.data.education.Option
import com.example.cliwatchjc.data.education.Question

@Composable
fun QuizScreen(articleId: Long, navController: NavController) {
    val articleViewModel: ArticleViewModel = hiltViewModel()
    val quizData by articleViewModel.quizData.collectAsState()
    val userScore by articleViewModel.userScore.collectAsState()
    val scrollState = rememberScrollState()

    // State to track if quiz data has been loaded
    var isQuizDataLoaded by remember { mutableStateOf(false) }

    // State to track selected options
    val selectedOptions = remember { mutableStateOf(mapOf<Long, Long>()) }

    if (!isQuizDataLoaded) {
        articleViewModel.loadQuizDataForArticle(articleId)
        isQuizDataLoaded = true
    }

    if (quizData.isNullOrEmpty()) {
        // Display a message if no quiz data is found
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text(text = "Quiz not found.", fontSize = 20.sp, fontWeight = FontWeight.Bold)
        }
    } else {
        // If quiz data is available, display the quiz questions and options
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .verticalScroll(scrollState),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Spacer(modifier = Modifier.height(56.dp))

            // Display user's score if available
            userScore?.let {
                Text(text = "Your score: ${it.score}", fontSize = 20.sp, fontWeight = FontWeight.Bold)
            }

            // Display each question and its options
            quizData.forEach { data ->
                QuestionAndOptionsView(question = data.question, options = data.options, selectedOptions)
            }

            // Add a button to submit the quiz
            Button(onClick = {
                val score = calculateScore(quizData, selectedOptions.value)
                articleViewModel.updateUserScore(articleId, score)

                // Format the route with arguments
                val routeWithArgs = "quizComplete/$score/${quizData.size}"
                Log.d("QUIZ_DEBUG", "Route with Args: $routeWithArgs")

                navController.navigate(routeWithArgs)
            }, modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)) {
                Text(text = "Submit Quiz")
            }
            Spacer(modifier = Modifier.height(80.dp))
        }
    }
}

@Composable
fun QuestionAndOptionsView(
    question: Question,
    options: List<Option>,
    selectedOptions: MutableState<Map<Long, Long>>
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(text = question.text, fontWeight = FontWeight.Bold)
        options.forEach { option ->
            OptionView(question.questionId, option, selectedOptions)
        }
    }
}

@Composable
fun OptionView(
    questionId: Long,
    option: Option,
    selectedOptions: MutableState<Map<Long, Long>>
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                val updatedMap = selectedOptions.value.toMutableMap()
                updatedMap[questionId] = option.optionId
                selectedOptions.value = updatedMap
            },
        verticalAlignment = Alignment.CenterVertically
    ) {
        RadioButton(
            selected = selectedOptions.value[questionId] == option.optionId,
            onClick = {
                val updatedMap = selectedOptions.value.toMutableMap()
                updatedMap[questionId] = option.optionId
                selectedOptions.value = updatedMap
            }
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(text = option.text)
    }
}

fun calculateScore(quizData: List<ArticleViewModel.QuestionWithOptions>, selectedOptions: Map<Long, Long>): Int {
    // Logic to calculate score based on selected options
    var score = 0
    quizData.forEach { data ->
        data.options.find { it.isCorrect && it.optionId == selectedOptions[data.question.questionId] }?.let { score++ }
    }
    return score
}

@Composable
fun QuizComplete(score: Int, totalQuestions: Int, navController: NavController) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column(
            modifier = Modifier.fillMaxSize().padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Quiz Submitted! Your score is",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "$score/$totalQuestions",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = { navController.navigate(Routes.EDUCATION_RESOURCES) }) {
                Text(text = "Back")
            }
        }
    }
}
