package com.example.cliwatchjc.modules.education

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.cliwatchjc.Routes
import com.example.cliwatchjc.data.education.Article

sealed class ArticleContentComponent {
    data class Paragraph(val text: String) : ArticleContentComponent()
    data class BulletPoint(val text: String) : ArticleContentComponent()
    data class Header(val text: String) : ArticleContentComponent()
    // ... other types like headers, images, quotes, etc.
}

@Composable
fun ArticleContentScreen(articleId: Long, navController: NavController) {
    val articleViewModel: ArticleViewModel = hiltViewModel()
    val article = articleViewModel.getArticle(articleId)
    val contentComponents = articleViewModel.getArticleContent(articleId)

    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(start = 16.dp, top = 16.dp, end = 16.dp)
    ) {
        Spacer(modifier = Modifier.height(56.dp))

        if (article != null) {
            Text(
                text = article.title,
                style = MaterialTheme.typography.titleLarge
            )
            Divider(color = Color.Gray, thickness = 4.dp, modifier = Modifier.padding(vertical = 8.dp))

            // Display the article content components
            contentComponents?.forEach { component ->
                when (component) {
                    is ArticleContentComponent.Paragraph -> Text(
                        text = component.text,
                        style = MaterialTheme.typography.bodySmall,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )

                    is ArticleContentComponent.BulletPoint -> Text(
                        text = "\u2022 ${component.text}",
                        style = MaterialTheme.typography.bodySmall,
                        modifier = Modifier.padding(bottom = 8.dp, start = 8.dp)
                    )
                    is ArticleContentComponent.Header -> {
                        Text(
                            text = component.text,
                            style = MaterialTheme.typography.headlineSmall,
                            modifier = Modifier.padding(bottom = 4.dp)
                        )
                        Divider(color = Color.Gray, thickness = 1.dp, modifier = Modifier.padding(bottom = 4.dp))
                    }
                    // TODO : Image
                }
            }

        } else {
            Text(
                text = "Article not found.",
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(bottom = 8.dp)
            )
        }

        Button(
            onClick = {
                navController.navigate("${Routes.QUIZ}/$articleId")
            },
            modifier = Modifier.fillMaxWidth().padding(top = 16.dp)
        ) {
            Text(text = "Take Quiz")
        }
        Spacer(modifier = Modifier.height(80.dp))
    }
}



