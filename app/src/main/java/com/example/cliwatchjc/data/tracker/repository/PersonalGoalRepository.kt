package com.example.cliwatchjc.data.tracker.repository

import com.example.cliwatchjc.data.tracker.PersonalGoal
import com.example.cliwatchjc.data.tracker.PersonalGoalDao

class PersonalGoalRepository (
    private val personalGoalDao: PersonalGoalDao,
) {
    // Methods for personal goals
    suspend fun getAllGoals(): List<PersonalGoal> {
        return personalGoalDao.getAllGoals()
    }

    suspend fun insertGoal(goal: PersonalGoal) = personalGoalDao.insertGoal(goal)

    suspend fun updateGoal(goal: PersonalGoal) {
        personalGoalDao.updateGoal(goal)
    }

    suspend fun deleteGoal(goal: PersonalGoal) {
        personalGoalDao.deleteGoal(goal)
    }
}

