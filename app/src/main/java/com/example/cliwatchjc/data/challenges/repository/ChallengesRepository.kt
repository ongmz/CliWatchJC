package com.example.cliwatchjc.data.challenges.repository

import com.example.cliwatchjc.data.challenges.AddChallenges
import com.example.cliwatchjc.data.challenges.ChallengesDao

class ChallengesRepository(private val challengesDao: ChallengesDao) {

    fun getAllChallenges(): List<AddChallenges> {
        return challengesDao.getAllChallenges()
    }

    fun setChallengeStatus(challengeId: Long, status: String) {
        challengesDao.setChallengeStatus(challengeId, status)
    }

    // Method to get the status of a challenge for a specific user
    fun getChallengeStatus(challengeId: Long): String? {
        return challengesDao.getChallengeStatus(challengeId)
    }

    fun insertChallenges(challenges: List<AddChallenges>) {
        challengesDao.insertChallenges(challenges)
    }
}
