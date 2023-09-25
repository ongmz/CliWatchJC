package com.example.cliwatchjc.data.education

import com.example.cliwatchjc.modules.education.ArticleContentComponent

object ArticleContentProvider {
    val articleContents: Map<Long, List<ArticleContentComponent>> = mapOf(
        1L to listOf(
            ArticleContentComponent.Paragraph("Climate change is one of the most pressing challenges facing humanity today. It refers to long-term shifts in global temperature and weather patterns, primarily driven by human activities. In this article, we will explore the causes and consequences of climate change."),

            ArticleContentComponent.Header("Causes of Climate Change:"),

            ArticleContentComponent.BulletPoint("Greenhouse Gas Emissions: The primary driver of climate change is the emission of greenhouse gases, such as carbon dioxide (CO2), methane (CH4), and nitrous oxide (N2O). These gases trap heat in the Earth's atmosphere, leading to a gradual increase in global temperatures."),

            ArticleContentComponent.BulletPoint("Deforestation: The cutting down of forests reduces the planet's capacity to absorb CO2, as trees play a crucial role in carbon sequestration."),

            ArticleContentComponent.BulletPoint("Burning of Fossil Fuels: The combustion of fossil fuels like coal, oil, and natural gas for energy production and transportation is a major source of CO2 emissions."),

            ArticleContentComponent.Header("Consequences of Climate Change:"),

            ArticleContentComponent.BulletPoint("Rising Temperatures: Global temperatures are on the rise, leading to more frequent and severe heatwaves. This can have detrimental effects on human health, agriculture, and ecosystems."),

            ArticleContentComponent.BulletPoint("Melting Ice and Rising Sea Levels: The warming climate is causing glaciers and polar ice caps to melt, contributing to rising sea levels. This poses a significant threat to coastal communities around the world."),

            ArticleContentComponent.BulletPoint("Extreme Weather Events: Climate change is linked to an increase in extreme weather events, including hurricanes, droughts, and floods, which can lead to significant economic and humanitarian impacts."),

            ArticleContentComponent.BulletPoint("Ecosystem Disruption: Changes in temperature and precipitation patterns can disrupt ecosystems, endangering biodiversity and threatening the availability of essential resources like clean water and food."),

            ArticleContentComponent.Paragraph("Understanding the causes and consequences of climate change is crucial for developing effective mitigation and adaptation strategies to address this global crisis.")
        ),
        2L to listOf(
            ArticleContentComponent.Paragraph("Paragraph text for Article 2."),
            ArticleContentComponent.BulletPoint("Bullet point text for Article 2."),
            ArticleContentComponent.Paragraph("Another paragraph for Article 2."),
            // ... other content components for this article
        )
    )

    fun getContentByArticleId(articleId: Long): List<ArticleContentComponent>? {
        return articleContents[articleId]
    }
}
