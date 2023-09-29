package com.example.cliwatchjc.data.tracker.repository

import com.example.cliwatchjc.data.tracker.PersonalGoalDetails
import com.example.cliwatchjc.data.tracker.PersonalGoalDetailsDao

class PersonalGoalDetailsRepository(private val personalGoalDetailsDAO: PersonalGoalDetailsDao) {
    // Methods for personal goal details
    suspend fun getGoalDetails(userId: Long, goalId: Long): List<PersonalGoalDetails> {
        return personalGoalDetailsDAO.getGoalDetails(userId, goalId)

    }
    suspend fun updateGoalDetails(personalGoalDetails: PersonalGoalDetails) =
        personalGoalDetailsDAO.updateGoalDetails(personalGoalDetails)


}


