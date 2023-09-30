package com.example.cliwatchjc.data.challenges

import com.example.cliwatchjc.modules.challenges.ChallengesContentComponent

object ChallengeContentProvider{
    val challengesContent: Map<Long, List<ChallengesContentComponent>> = mapOf(
        1L to listOf(
            ChallengesContentComponent.Paragraph("For decades, the phrase \"climate change\" has echoed through scientific discussions, policy debates, and global headlines. It's more than just a term; it's a reality, signaling long-term shifts and alterations in our planet's climate. But how did we get here?"),

            ChallengesContentComponent.Header("1. The Natural and Enhanced Greenhouse Effect:"),
            ChallengesContentComponent.Paragraph("The Earth has a set of atmospheric gases, such as water vapor, carbon dioxide, methane, and nitrous oxide, which act like a blanket, trapping the sun's heat. This is known as the greenhouse effect. While the natural greenhouse effect is essential for sustaining life, an excessive concentration of these gases from human activities has accelerated and intensified this effect. As a result, our planet's average temperature has been rising at an alarming rate."),

            ChallengesContentComponent.Header("2. The Carbon Culprit:"),
            ChallengesContentComponent.Paragraph("One primary offender in this scenario is carbon dioxide (CO2). Emitted largely from industrial processes, vehicle emissions, and deforestation, CO2 levels have surged over the past century, leading to heightened global warming."),

            ChallengesContentComponent.Header("3. Observable Impacts:"),
            ChallengesContentComponent.BulletPoint("Polar ice caps and glaciers are melting, contributing to rising sea levels."),
            ChallengesContentComponent.BulletPoint("Extreme weather patterns, from scorching heatwaves to devastating floods, have become the new norm."),
            ChallengesContentComponent.BulletPoint("Biodiversity loss is accelerated as habitats are disrupted."),
            ChallengesContentComponent.BulletPoint("The chemistry of our oceans is changing, leading to acidification and endangering marine life."),
        ),
        2L to listOf(
            ChallengesContentComponent.Paragraph("A few degrees might seem insignificant, but in the vast play of Earth's climate, even small temperature changes can have profound effects."),
            ChallengesContentComponent.Header("1. Not Just Hotter Summers:"),
            ChallengesContentComponent.Paragraph("Climate change isn't just about sweltering summer days. The rising temperatures can lead to erratic weather patterns. Regions accustomed to rainfall may face droughts, while others might encounter unprecedented flooding."),

            ChallengesContentComponent.Header("2. Rising Oceans:"),
            ChallengesContentComponent.Paragraph("Melting glaciers don't just affect polar bears; they lead to rising sea levels. This endangers coastal cities, causing increased flooding, threatening freshwater resources, and even potentially displacing populations."),

            ChallengesContentComponent.Header("3. Shifting Habitats and Loss of Biodiversity:"),
            ChallengesContentComponent.Paragraph("Many animals and plants have specific habitats. With changing climates, these habitats either shift or disappear, pushing many species towards extinction. Coral reefs, often dubbed the \"rainforests of the sea,\" are especially vulnerable, with rising temperatures causing damaging bleaching events.")
        )
    )
    fun getContentByChallengesId(challengesId: Long): List<ChallengesContentComponent>? {
        return challengesContent[challengesId]
    }
}