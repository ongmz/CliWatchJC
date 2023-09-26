package com.example.cliwatchjc.data.education

import com.example.cliwatchjc.modules.education.ArticleContentComponent

object ArticleContentProvider {
    val articleContents: Map<Long, List<ArticleContentComponent>> = mapOf(
        1L to listOf(
            ArticleContentComponent.Paragraph("For decades, the phrase \"climate change\" has echoed through scientific discussions, policy debates, and global headlines. It's more than just a term; it's a reality, signaling long-term shifts and alterations in our planet's climate. But how did we get here?"),

            ArticleContentComponent.Header("1. The Natural and Enhanced Greenhouse Effect:"),
            ArticleContentComponent.Paragraph("The Earth has a set of atmospheric gases, such as water vapor, carbon dioxide, methane, and nitrous oxide, which act like a blanket, trapping the sun's heat. This is known as the greenhouse effect. While the natural greenhouse effect is essential for sustaining life, an excessive concentration of these gases from human activities has accelerated and intensified this effect. As a result, our planet's average temperature has been rising at an alarming rate."),

            ArticleContentComponent.Header("2. The Carbon Culprit:"),
            ArticleContentComponent.Paragraph("One primary offender in this scenario is carbon dioxide (CO2). Emitted largely from industrial processes, vehicle emissions, and deforestation, CO2 levels have surged over the past century, leading to heightened global warming."),

            ArticleContentComponent.Header("3. Observable Impacts:"),
            ArticleContentComponent.BulletPoint("Polar ice caps and glaciers are melting, contributing to rising sea levels."),
            ArticleContentComponent.BulletPoint("Extreme weather patterns, from scorching heatwaves to devastating floods, have become the new norm."),
            ArticleContentComponent.BulletPoint("Biodiversity loss is accelerated as habitats are disrupted."),
            ArticleContentComponent.BulletPoint("The chemistry of our oceans is changing, leading to acidification and endangering marine life."),
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
