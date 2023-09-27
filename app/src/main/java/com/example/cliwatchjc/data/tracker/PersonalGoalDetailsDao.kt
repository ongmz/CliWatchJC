package com.example.cliwatchjc.data.tracker

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface PersonalGoalDetailsDao {

    @Query("SELECT * FROM PersonalGoalDetails where userId = :userId AND goalId = :goalId")
    fun getGoalDetails(userId: Long, goalId: Long): List<PersonalGoalDetails>

    @Update
    fun updateGoalDetails(goalDetails: PersonalGoalDetails)
}
