package com.example.cliwatchjc.data.tracker.repository

import com.example.cliwatchjc.data.tracker.PersonalGoalDetails
import com.example.cliwatchjc.data.tracker.PersonalGoalDetailsDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PersonalGoalDetailsRepository(private val personalGoalDetailsDAO: PersonalGoalDetailsDao) {
    // Methods for personal goal details
    fun getGoalDetails(userId: Long, goalId: Long): List<PersonalGoalDetails> {
        return personalGoalDetailsDAO.getGoalDetails(userId, goalId)

    }
    fun updateGoalDetails(personalGoalDetails: PersonalGoalDetails) =
        personalGoalDetailsDAO.updateGoalDetails(personalGoalDetails)


}


