package com.example.cliwatchjc.data.tracker

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Update

@Dao
interface PersonalGoalDetailsDao {

    @Query("SELECT * FROM PersonalGoalDetails where userId = :userId AND goalId = :goalId")
    suspend fun getGoalDetails(userId: Int, goalId: Long): List<PersonalGoalDetails>

    @Update
    suspend fun updateGoalDetails(goalDetails: PersonalGoalDetails)
}
