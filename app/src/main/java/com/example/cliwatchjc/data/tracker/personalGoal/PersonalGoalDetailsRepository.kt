package com.example.cliwatchjc.data.tracker.personalGoal

import android.app.Application
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PersonalGoalDetailsRepository(application: Application) {
    private val personalGoalDetailsDAO: PersonalGoalDetailsDao

    init {
        val database: PersonalGoalDatabase = PersonalGoalDatabase.getInstance(application)
        personalGoalDetailsDAO = database.personalGoalDetailsDAO()
    }

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
