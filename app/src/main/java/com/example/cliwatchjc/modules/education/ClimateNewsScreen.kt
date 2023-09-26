package com.example.cliwatchjc.modules.education

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import com.example.cliwatchjc.Routes
import com.example.cliwatchjc.data.education.ClimateNews

@Composable
fun ClimateNewsScreen(navController: NavController) {
    val newsViewModel: NewsViewModel = hiltViewModel()
    val newsList by newsViewModel.newsList.collectAsState()

    if (newsList.isEmpty()) {
        Text("Loading...")
    } else {
        LazyColumn (Modifier.padding(top = 68.dp)) {
            items(newsList) { newsItem: ClimateNews ->
                NewsItemRow(newsItem) {
                    navigateToWebView(navController, newsItem.url)
                }
            }
        }
    }
}

@Composable
fun NewsItemRow(newsItem: ClimateNews, onItemClicked: (String) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp, bottom = 8.dp, start = 16.dp, end = 16.dp)
            .clickable { onItemClicked(newsItem.url) },
        elevation = CardDefaults.cardElevation(defaultElevation = 10.dp),
        shape = MaterialTheme.shapes.medium
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            newsItem.urlToImage?.let { imageUrl ->
                // Using a simple Image composable here; you might want to consider using Coil or Glide with Accompanist library for loading remote images
                Image(
                    painter = rememberAsyncImagePainter(model = imageUrl),
                    contentDescription = null,
                    modifier = Modifier
                        .size(96.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(MaterialTheme.colorScheme.onSurface.copy(alpha = 0.12f))
                )
                Spacer(modifier = Modifier.width(16.dp))
            }

            Column {
                Text(text = newsItem.title, style = MaterialTheme.typography.titleSmall)
                Divider(color = Color.Gray, modifier = Modifier.padding(vertical = 4.dp))
                Text(text = newsItem.description ?: "", maxLines = 2, overflow = TextOverflow.Ellipsis, lineHeight = 16.sp)
            }
        }
    }
}

fun navigateToWebView(navController: NavController, url: String) {
    navController.navigate("webViewScreen?url=$url")
}


