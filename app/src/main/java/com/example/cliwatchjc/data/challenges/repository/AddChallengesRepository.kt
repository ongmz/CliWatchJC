package com.example.cliwatchjc.data.challenges.repository

import com.example.cliwatchjc.data.challenges.AddChallenges
import com.example.cliwatchjc.data.challenges.AddChallengesDao
import kotlinx.coroutines.flow.Flow

class AddChallengesRepository(private val addChallengesDao: AddChallengesDao) {

    fun getAllChallenges(): Flow<List<AddChallenges>> {
        return addChallengesDao.getAllChallenges()
    }

    fun insertChallenge(challenge: AddChallenges) {

        val challenge1 = AddChallenges(
            challengesId = 10001,
            challenges_title = "Green Commute Challenge",
            challenges_desc = "Encourage employees to use eco-friendly transportation options such as cycling, carpooling, or public transit for their daily commute. Participants will track their green commutes for a month and share their experiences on social media using a dedicated hashtag.",
            challenges_duration = "Daily",
            challenges_status = "AVAILABLE",
            marks = 100
        )

        val challenge2 = AddChallenges(
            challengesId = 10002,
            challenges_title = "Renewable Energy Innovation",
            challenges_desc = "Invite teams of engineers and scientists to develop innovative solar energy solutions. The challenge includes designing more efficient solar panels, energy storage systems, or solar-powered gadgets. The winning team's technology will be funded for further development.",
            challenges_duration = "Monthly",
            challenges_status = "AVAILABLE",
            marks = 150
        )

        val challenge3 = AddChallenges(
            challengesId = 10003,
            challenges_title = "Reforestation Initiative",
            challenges_desc = "Organize a community-based reforestation initiative to combat deforestation and restore local ecosystems. Participants will plant native trees in designated areas and monitor their growth over a year. The challenge aims to plant 10,000 trees in the region.",
            challenges_duration = "Weekly",
            challenges_status = "AVAILABLE",
            marks = 200
        )

        addChallengesDao.insertChallenge(challenge1)
        addChallengesDao.insertChallenge(challenge2)
        addChallengesDao.insertChallenge(challenge3)
    }

    fun updateChallengeStatusAndMarks(challenge: AddChallenges, newStatus: String, marks: Int) {

    }
}

