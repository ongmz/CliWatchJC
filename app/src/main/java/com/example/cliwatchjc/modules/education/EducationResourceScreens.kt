package com.example.cliwatchjc.modules.education

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.compose.rememberNavController
import com.example.cliwatchjc.Routes
import com.example.cliwatchjc.UserManager

import com.example.cliwatchjc.data.education.Article
import com.example.cliwatchjc.data.education.EducationDao
import com.example.cliwatchjc.data.education.repository.ArticleRepository

@Composable
fun EducationResourcesScreen(navController: NavController) {
    val articleViewModel: ArticleViewModel = hiltViewModel()
    val currentUser = articleViewModel.currentUser

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 56.dp)
    ) {
        ArticleList(articleViewModel, currentUser, navController)
    }
}

@Composable
fun ArticleList(articleViewModel: ArticleViewModel, currentUser: UserManager.User?, navController: NavController) {
    val articles = articleViewModel.articles.collectAsState().value

    LazyColumn {
        items(articles) { article ->
            ArticleItem(article = article, currentUser = currentUser, articleViewModel = articleViewModel, navController = navController)
        }
    }
}

@Composable
fun ArticleItem(article: Article, currentUser: UserManager.User?, articleViewModel: ArticleViewModel, navController: NavController) {
    // Load the user's score for this article when this composable is displayed or recomposed
    LaunchedEffect(article.articleId, currentUser?.userId) {
        currentUser?.let {
            articleViewModel.loadUserScoreForArticle(it.userId, article.articleId)
        }
    }
    val userScore = articleViewModel.userScore.collectAsState().value?.score ?: 0

    Box(
        modifier = Modifier
            .padding(16.dp)
            .height(96.dp)
            .fillMaxWidth()
            .shadow(4.dp, RoundedCornerShape(4.dp))
            .background(Color.White, RoundedCornerShape(4.dp))
            .clickable {
                navController.navigate("${Routes.ARTICLE_CONTENT}/${article.articleId}")
            }
    ) {
        // Display the article title
        Text(
            text = article.title,
            style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold, fontSize = 24.sp),
            modifier = Modifier.align(Alignment.TopStart).padding(top = 12.dp, start = 8.dp)
        )

        // Display the max score at the bottom right
        Box(
            modifier = Modifier.fillMaxWidth().align(Alignment.BottomEnd).padding(8.dp),
            contentAlignment = Alignment.BottomEnd
        ) {
            Text(
                text = "Score: $userScore/${article.maxScore}",
                color = Color.Blue,
                fontWeight = FontWeight.Bold
            )
        }
    }
}




