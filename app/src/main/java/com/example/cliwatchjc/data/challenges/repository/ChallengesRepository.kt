package com.example.cliwatchjc.data.challenges.repository

import com.example.cliwatchjc.data.challenges.Challenges
import com.example.cliwatchjc.data.challenges.ChallengesDao


class ChallengesRepository(private val challengesDao: ChallengesDao) {
    fun getAllChallenges(): List<Challenges> = challengesDao.getAllChallenges()

    fun getChallengesById(id: Long): Challenges = challengesDao.getChallengesById(id)

    fun insertChallenges(challenges: Challenges) = challengesDao.insertChallenges(challenges)

    fun deleteChallenges(challenges: Challenges) = challengesDao.deleteChallenges(challenges)

    fun insertSampleChallenges() {
        val challenges = listOf(
            Challenges(challengesId = 1L, title = "Reduce Your Carbon Footprint", content = "using public transportation in your daily life.", "Available"),
            Challenges(challengesId = 2L, title = "Plant a Tree for a Greener Future", content = "planting a tree in your community", "Available"),
            Challenges(challengesId = 3L, title = "Reduce Single-Use Plastics", content = "Commit to reducing single-use plastics", "Available")
        )
        for (challenge in challenges) {
            challengesDao.insertChallenges(challenge)
        }
    }
    // Updated function to use String for completed field
    fun updateChallenge(challenge: Challenges) {
        // Perform the update operation on the database or data source
        challengesDao.updateChallenge(challenge)
    }

}

