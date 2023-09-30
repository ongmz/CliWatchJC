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
            Challenges(challengesId = 1, title = "Climate Change Decoded", content = "The Basics and Beyond", maxScore = 3),
            Challenges(challengesId = 2, title = "Ripples of a Warming World", content = "Far-reaching Consequences of Climate Change", maxScore = 3),
            Challenges(challengesId = 3, title = "Leading the Charge", content = "How We Can Combat Climate Change", maxScore = 5)
        )
        for (challenge in challenges) {
            challengesDao.insertChallenges(challenge)
        }
    }
}

