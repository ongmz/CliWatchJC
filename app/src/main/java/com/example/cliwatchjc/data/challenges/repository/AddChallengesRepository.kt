package com.example.cliwatchjc.data.challenges.repository

import com.example.cliwatchjc.data.challenges.AddChallenges
import com.example.cliwatchjc.data.challenges.AddChallengesDao
import kotlinx.coroutines.flow.Flow

class AddChallengesRepository(private val addChallengesDao: AddChallengesDao) {

    fun getAllChallenges(): Flow<List<AddChallenges>> {
        return addChallengesDao.getAllChallenges()
    }

    suspend fun insertChallenge(challenge: AddChallenges) {
        addChallengesDao.insertChallenge(challenge)
    }

    fun updateChallengeStatusAndMarks(challenge: AddChallenges, newStatus: String, marks: Int) {

    }
}

