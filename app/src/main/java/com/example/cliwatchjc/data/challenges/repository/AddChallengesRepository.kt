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

    suspend fun insertChallenge(challenge: AddChallenges) {
        withContext(Dispatchers.IO){
            addChallengesDao.insertChallenge(challenge)
        }
    }

    suspend fun updateChallengeStatusAndMarks(challengeId: Long, newStatus: String, marks: Int) {
        withContext(Dispatchers.IO){
            addChallengesDao.updateChallengeStatusAndMarks(challengeId, newStatus, marks)
        }
    }
}

