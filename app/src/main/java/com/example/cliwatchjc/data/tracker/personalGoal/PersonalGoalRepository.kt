package com.example.cliwatchjc.data.tracker.personalGoal

import android.app.Application
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PersonalGoalRepository(application: Application) {
    private val goalDAO: PersonalGoalDao

    init {
        val database: PersonalGoalDatabase = PersonalGoalDatabase.getInstance(application)
        goalDAO = database.personalGoalDAO()
    }

    fun getAllGoals(): List<PersonalGoal> {
        return goalDAO.getAllGoals()
    }

    suspend fun insertGoal(goal: PersonalGoal) {
        withContext(Dispatchers.IO) {
            goalDAO.insertGoal(goal)
        }
    }

    suspend fun updateGoal(goal: PersonalGoal) {
        withContext(Dispatchers.IO) {
            goalDAO.updateGoal(goal)
        }
    }

    suspend fun unSelectAllGoals() {
        withContext(Dispatchers.IO) {
            goalDAO.unSelectAllGoals(false)
        }
    }

    suspend fun deleteGoal(goal: PersonalGoal) {
        withContext(Dispatchers.IO) {
            goalDAO.deleteGoal(goal)
        }
    }
}
