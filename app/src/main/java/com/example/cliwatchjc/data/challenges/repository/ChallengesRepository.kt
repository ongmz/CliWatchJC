package com.example.cliwatchjc.data.challenges.repository

import com.example.cliwatchjc.data.challenges.AddChallenges
import com.example.cliwatchjc.data.challenges.ChallengesDao

class ChallengesRepository(private val challengesDao: ChallengesDao) {

    suspend fun getAllChallenges(): List<AddChallenges> { // Update return type
        return challengesDao.getAllChallenges()
    }

    suspend fun setChallengeStatus(challengeId: Long, status: String) {
        challengesDao.setChallengeStatus(challengeId, status)
    }

    // Method to get the status of a challenge for a specific user
    suspend fun getChallengeStatus(challengeId: Long): String? {
        return challengesDao.getChallengeStatus(challengeId)
    }

    suspend fun insertChallenges(challenges: List<AddChallenges>){
        challengesDao.insertChallenges(challenges)
    }


}


/*
class NewsRepository(private val educationDao: EducationDao) {
    suspend fun getAllNewsReports(): List<NewsReport> {
        // Implement the logic to fetch news reports here
    }

    // Add methods for creating, updating, and deleting news reports as needed
}
*/