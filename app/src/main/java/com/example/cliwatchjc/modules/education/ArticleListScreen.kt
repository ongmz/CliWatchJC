package com.example.cliwatchjc.modules.education

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.TopStart
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.cliwatchjc.Routes
import com.example.cliwatchjc.UserManager

import com.example.cliwatchjc.data.education.Article
import com.example.cliwatchjc.data.education.EducationDao
import com.example.cliwatchjc.data.education.UserQuizScore

@Composable
fun ArticleListScreen(navController: NavController) {
    val articleViewModel: ArticleViewModel = hiltViewModel()
    val currentUser = articleViewModel.currentUser
    val articles = articleViewModel.articles.collectAsState().value

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 56.dp)
    ) {
        ArticleList(articles, currentUser, articleViewModel, navController)
    }
}

@Composable
fun ArticleList(articles: List<Article>, currentUser: UserManager.User?, articleViewModel: ArticleViewModel, navController: NavController) {
    val userScore = articleViewModel.userScore.collectAsState().value

    LazyColumn {
        items(articles) { article ->
            ArticleItem(article, currentUser, articleViewModel, navController)
        }
    }
}

@Composable
fun ArticleItem(
    article: Article,
    currentUser: UserManager.User?,
    articleViewModel: ArticleViewModel,
    navController: NavController
) {
    val userScore = articleViewModel
        .getUserScoreForArticle(currentUser?.userId ?: -1, article.articleId)
        .collectAsState(initial = null).value

    val score = userScore?.score ?: 0

    Card(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
            .clickable {
                navController.navigate("${Routes.ARTICLE_CONTENT}/${article.articleId}")
            },
        elevation = CardDefaults.cardElevation(defaultElevation = 10.dp),
        shape = RoundedCornerShape(4.dp)
    ) {
        Box(
            modifier = Modifier
                .height(96.dp)
                .fillMaxWidth()
        ) {
            // Display the article title and content
            Column(
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = article.title,
                    style = MaterialTheme.typography.titleLarge.copy(
                        fontWeight = FontWeight.Bold,
                        fontSize = 24.sp
                    ),
                    modifier = Modifier
                        .padding(top = 12.dp, start = 8.dp)
                )
                Text(
                    text = article.content,
                    fontSize = 12.sp,
                    modifier = Modifier
                        .padding(start = 8.dp)
                )
            }

            // Display the max score at the bottom right
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomEnd)
                    .padding(8.dp),
                contentAlignment = Alignment.BottomEnd
            ) {
                Text(
                    text = "Score: $score/${article.maxScore}",
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}





