package com.example.cliwatchjc.data.tracker.personalGoal

import com.example.cliwatchjc.data.tracker.personalGoal.PersonalGoalDetails
import com.example.cliwatchjc.data.tracker.personalGoal.PersonalGoalDetailsDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PersonalGoalDetailsRepository(private val personalGoalDetailsDAO: PersonalGoalDetailsDao) {
    // Methods for personal goal details
    suspend fun getGoalDetails(goalId: Long): List<PersonalGoalDetails> {
        return withContext(Dispatchers.IO) {
            personalGoalDetailsDAO.getGoalDetails(goalId)
        }
    }

    suspend fun insertGoalDetails(personalGoalDetails: PersonalGoalDetails) {
        withContext(Dispatchers.IO) {
            personalGoalDetailsDAO.insertGoalDetails(personalGoalDetails)
        }
    }

    suspend fun updateGoalDetails(personalGoalDetails: PersonalGoalDetails) {
        withContext(Dispatchers.IO) {
            personalGoalDetailsDAO.updateGoalDetails(personalGoalDetails)
        }
    }
}


