package com.example.cliwatchjc.data.challenges.repository

import com.example.cliwatchjc.data.challenges.AddChallenges
import com.example.cliwatchjc.data.challenges.AddChallengesDao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


class AddChallengesRepository(private val addChallengesDao: AddChallengesDao) {

    fun getAllChallenges(): Flow<List<AddChallenges>> {
        return addChallengesDao.getAllChallenges()
    }

    suspend fun insertChallenge(challenge: AddChallenges): Boolean {
        return try {
            addChallengesDao.insertChallenge(challenge)
            true // Return true to indicate successful insertion
        } catch (e: Exception) {
            false // Return false to indicate insertion failure
        }
    }


    suspend fun updateChallengeStatusAndMarks(challengeId: Long, newStatus: String, marks: Int) {
        withContext(Dispatchers.IO){
            addChallengesDao.updateChallengeStatusAndMarks(challengeId, newStatus, marks)
        }
    }
}

