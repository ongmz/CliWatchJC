package com.example.cliwatchjc.data.challenges

import com.example.cliwatchjc.modules.challenges.ChallengesContentComponent

object ChallengeContentProvider{
    val challengesContent: Map<Long, List<ChallengesContentComponent>> = mapOf(
        1L to listOf(
            ChallengesContentComponent.Header("Take Action to Reduce Emissions\n"),
            ChallengesContentComponent.Paragraph("In this challenge, you'll take important steps to reduce your carbon footprint and minimize your impact on the environment. By adopting sustainable practices and making conscious choices, you can contribute to the fight against climate change.\n"),

            ChallengesContentComponent.BulletPoint("Use Energy Efficiently: Turn off lights and appliances when not in use, and consider switching to LED bulbs.\n"),
            ChallengesContentComponent.BulletPoint("Choose Sustainable Transportation: Opt for public transportation, carpooling, or biking to reduce greenhouse gas emissions from personal vehicles.\n"),
            ChallengesContentComponent.BulletPoint("Conserve Water: Be mindful of water usage and fix any leaks in your home.\n"),
            ChallengesContentComponent.BulletPoint("Support Renewable Energy: Explore renewable energy sources like solar or wind power for your home.\n"),
        ),
        2L to listOf(
            ChallengesContentComponent.Header("Plant a Tree for a Greener Future\n"),
            ChallengesContentComponent.Paragraph("Join the movement to combat deforestation and promote a greener future. In this challenge, you'll have the opportunity to make a lasting impact by planting a tree in your community. Trees play a vital role in capturing carbon dioxide and enhancing air quality.\n"),

            ChallengesContentComponent.BulletPoint("Why Trees Matter: Trees absorb carbon dioxide, release oxygen, and provide habitat for wildlife.\n"),
            ChallengesContentComponent.BulletPoint("Local Planting Events: Participate in tree-planting events organized by environmental groups or your community.\n"),
            ChallengesContentComponent.BulletPoint("Tree Selection: Choose native tree species that thrive in your region for better sustainability.\n")
        ),
        3L to listOf(
            ChallengesContentComponent.Header("Say No to Single-Use Plastics\n"),
            ChallengesContentComponent.Paragraph("In this challenge, you'll commit to reducing the use of single-use plastics, which contribute significantly to environmental pollution. By adopting eco-friendly alternatives and raising awareness, you can make a positive impact on the planet.\n"),

            ChallengesContentComponent.BulletPoint("Eco-Friendly Shopping: Bring your reusable bags when shopping and say no to plastic bags.\n"),
            ChallengesContentComponent.BulletPoint("Reusable Containers: Invest in reusable water bottles and food containers to minimize plastic waste.\n"),
            ChallengesContentComponent.BulletPoint("Support Plastic-Free Initiatives: Join campaigns advocating for reduced plastic use in your community.\n")
        )
    )
    fun getContentByChallengesId(challengesId: Long): List<ChallengesContentComponent>? {
        return challengesContent[challengesId]
    }
}