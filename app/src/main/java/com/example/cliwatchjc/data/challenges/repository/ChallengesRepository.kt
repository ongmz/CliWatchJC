package com.example.cliwatchjc.data.challenges.repository


import com.example.cliwatchjc.data.challenges.Challenges
import com.example.cliwatchjc.data.challenges.ChallengesDao

class ChallengesRepository(private val challengesDao: ChallengesDao) {
    // Methods for articles
    suspend fun getAllChallenges(): List<Challenges>{
        return challengesDao.getAllChallenges()
    }
    suspend fun setChallengeStatus(userId: Long, challengeId: Long, status: String) {
        challengesDao.setChallengeStatus(userId, challengeId, status)
    }

    // Method to get the status of a challenge for a specific user
    suspend fun getChallengeStatus(userId: Long, challengeId: Long): String? {
        return challengesDao.getChallengeStatus(userId, challengeId)
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