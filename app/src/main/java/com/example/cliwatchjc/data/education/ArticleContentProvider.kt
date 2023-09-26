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
            ArticleContentComponent.Paragraph("A few degrees might seem insignificant, but in the vast play of Earth's climate, even small temperature changes can have profound effects."),
            ArticleContentComponent.Header("1. Not Just Hotter Summers:"),
            ArticleContentComponent.Paragraph("Climate change isn't just about sweltering summer days. The rising temperatures can lead to erratic weather patterns. Regions accustomed to rainfall may face droughts, while others might encounter unprecedented flooding."),

            ArticleContentComponent.Header("2. Rising Oceans:"),
            ArticleContentComponent.Paragraph("Melting glaciers don't just affect polar bears; they lead to rising sea levels. This endangers coastal cities, causing increased flooding, threatening freshwater resources, and even potentially displacing populations."),

            ArticleContentComponent.Header("3. Shifting Habitats and Loss of Biodiversity:"),
            ArticleContentComponent.Paragraph("Many animals and plants have specific habitats. With changing climates, these habitats either shift or disappear, pushing many species towards extinction. Coral reefs, often dubbed the \"rainforests of the sea,\" are especially vulnerable, with rising temperatures causing damaging bleaching events.")
        ),
        3L to listOf(
            ArticleContentComponent.Paragraph("As climate change accelerates, its consequences grow more apparent, challenging us to take deliberate and meaningful action. While the road might seem daunting, there are a plethora of strategies available to curtail its impact and pave the way for a more sustainable future."),

            ArticleContentComponent.Header("1. Cutting Down the Carbon:"),
            ArticleContentComponent.Paragraph("The release of carbon into the atmosphere, mainly from burning fossil fuels like coal, oil, and gas, is a primary driver of global warming. Addressing our carbon footprint requires a multipronged approach:"),
            ArticleContentComponent.BulletPoint("Transitioning to Renewable Energy: Solar, wind, and hydroelectric power offer sustainable energy alternatives that reduce reliance on fossil fuels."),
            ArticleContentComponent.BulletPoint("Energy Efficiency: Implementing energy-saving practices in homes, offices, and industries can drastically cut down emissions. Simple measures like LED lighting, energy-efficient appliances, and improved insulation can make a substantial difference."),
            ArticleContentComponent.BulletPoint("Sustainable Transportation: Encouraging the use of electric vehicles, supporting public transport, and developing infrastructure for biking and walking can further reduce emissions."),

            ArticleContentComponent.Header("2. The Green Revolution:"),
            ArticleContentComponent.Paragraph("Nature has its own mechanisms to combat excess carbon, and trees play a pivotal role in this:"),
            ArticleContentComponent.BulletPoint("Reforestation and Afforestation: Planting trees in areas where they were cut down or in places that were never forested can absorb vast amounts of CO2."),
            ArticleContentComponent.BulletPoint("Urban Green Spaces: Parks, green rooftops, and community gardens not only provide recreational spaces but also contribute to carbon absorption and combat the \"urban heat island\" effect."),

            ArticleContentComponent.Header("3. Policy & Innovation:"),
            ArticleContentComponent.Paragraph("Mere recognition of the problem isn't enough; effective solutions require robust policy frameworks and technological breakthroughs:"),
            ArticleContentComponent.BulletPoint("Governmental Policies: Governments can levy taxes on carbon emissions, offer incentives for green practices, and invest in research for sustainable technologies."),
            ArticleContentComponent.BulletPoint("Green Technologies: Innovations like carbon capture and storage, sustainable agriculture practices, and biodegradable materials can make a profound impact on our environment.")
        )
    )

    fun getContentByArticleId(articleId: Long): List<ArticleContentComponent>? {
        return articleContents[articleId]
    }
}
