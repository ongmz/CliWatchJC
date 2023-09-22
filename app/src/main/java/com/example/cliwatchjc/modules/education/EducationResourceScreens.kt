package com.example.cliwatchjc.modules.education

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.cliwatchjc.Routes
import com.example.cliwatchjc.data.education.Article
import com.example.cliwatchjc.data.education.EducationDao
import com.example.cliwatchjc.data.education.repository.ArticleRepository

@Composable
fun EducationResourcesScreen() {
    val articleViewModel: ArticleViewModel = hiltViewModel()
    ArticleList(articleViewModel)
}

@Composable
fun ArticleList(articleViewModel: ArticleViewModel = viewModel()) {
    // Observe the articles from the ViewModel
    val articles = articleViewModel.articles.collectAsState().value

    // Use LazyColumn to display the list of articles
    LazyColumn {
        items(articles) { article ->
            ArticleItem(article = article)
        }
    }
}

@Composable
fun ArticleItem(article: Article) {
    Box(modifier = Modifier.padding(16.dp).fillMaxWidth().border(1.dp, Color.Gray)) {
        Column(
            modifier = Modifier.fillMaxSize().padding(8.dp)
        ) {
            // Display the article title
            Text(
                text = article.title,
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            // Display the article content
            Text(
                text = article.content,
                modifier = Modifier.weight(1f).padding(bottom = 8.dp)
            )

            // Display the max score at the bottom right
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.BottomEnd
            ) {
                Text(
                    text = "Score: ${article.maxScore}/5",
                    color = Color.Blue,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}


