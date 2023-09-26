package com.example.cliwatchjc.data.tracker.repository

import com.example.cliwatchjc.data.tracker.PersonalGoal
import com.example.cliwatchjc.data.tracker.PersonalGoalDao

class PersonalGoalRepository (
    private val personalGoalDao: PersonalGoalDao,
) {
    // Methods for personal goals
    fun getAllGoals(): List<PersonalGoal> {
        return personalGoalDao.getAllGoals()
    }

    fun getGoal(id: Long): PersonalGoal {
        return personalGoalDao.getGoal(id)
    }

    fun insertGoal(goal: PersonalGoal) = personalGoalDao.insertGoal(goal)

    fun updateGoal(goal: PersonalGoal) {
        personalGoalDao.updateGoal(goal)
    }

    fun unSelectAllGoals() {
        personalGoalDao.unSelectAllGoals(false)
    }

    fun deleteGoal(goal: PersonalGoal) {
        personalGoalDao.deleteGoal(goal)
    }

}

