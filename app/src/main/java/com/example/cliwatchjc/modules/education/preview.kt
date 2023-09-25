package com.example.cliwatchjc.modules.education

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.cliwatchjc.data.education.Article

@Composable
fun ArticleContentScreen(articleId: Long, getArticle: (Long) -> Article?) {
    val article = getArticle(articleId)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 16.dp, top = 16.dp)
    ) { Spacer(modifier = Modifier.height(56.dp))

        if (article != null) {
            // Display the article title
            Text(
                text = article.title,
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            // Display the article content
            Text(
                text = article.content,
                modifier = Modifier.weight(1f).padding(bottom = 8.dp)
            )
        } else {
            Text(
                text = "Article not found.",
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(bottom = 8.dp)
            )
        }
    }
}

@Preview(showBackground = true, name = "Article Content Screen")
@Composable
fun PreviewArticleContentScreen() {
    // Sample data...
    val mockArticle = Article(
        articleId = 1L,
        title = "Understanding Climate Change: Causes and Consequences",
        content = "Climate change is one of the most pressing challenges facing humanity today. " +
                  "It refers to long-term shifts in global temperature and weather patterns, " +
                  "primarily driven by human activities. In this article, we will explore " +
                  "the causes and consequences of climate change.\n" +

                  "\nCauses of Climate Change:\n" +
                  "1. Greenhouse Gas Emissions: The primary driver of climate change is the " +
                  "emission of greenhouse gases, such as carbon dioxide (CO2), methane (CH4), " +
                  "and nitrous oxide (N2O). These gases trap heat in the Earth's atmosphere, " +
                  "leading to a gradual increase in global temperatures.\n" +
                  "\n" +
                  "Deforestation: The cutting down of forests reduces the planet's capacity to absorb CO2, as trees play a crucial role in carbon sequestration.\n" +
                "\n" +
                "Burning of Fossil Fuels: The combustion of fossil fuels like coal, oil, and natural gas for energy production and transportation is a major source of CO2 emissions.\n" +
                "\n" +
                "Consequences of Climate Change:\n" +
                "\n" +
                "Rising Temperatures: Global temperatures are on the rise, leading to more frequent and severe heatwaves. This can have detrimental effects on human health, agriculture, and ecosystems.\n" +
                "\n" +
                "Melting Ice and Rising Sea Levels: The warming climate is causing glaciers and polar ice caps to melt, contributing to rising sea levels. This poses a significant threat to coastal communities around the world.\n" +
                "\n" +
                "Extreme Weather Events: Climate change is linked to an increase in extreme weather events, including hurricanes, droughts, and floods, which can lead to significant economic and humanitarian impacts.\n" +
                "\n" +
                "Ecosystem Disruption: Changes in temperature and precipitation patterns can disrupt ecosystems, endangering biodiversity and threatening the availability of essential resources like clean water and food.\n" +
                "\n" +
                "Understanding the causes and consequences of climate change is crucial for developing effective mitigation and adaptation strategies to address this global crisis.",
        maxScore = 5
    )

    ArticleContentScreen(
        articleId = 1L,
        getArticle = { id -> if (id == mockArticle.articleId) mockArticle else null }
    )
}