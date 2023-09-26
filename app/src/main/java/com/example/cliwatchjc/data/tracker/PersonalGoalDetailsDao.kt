package com.example.cliwatchjc.data.tracker

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.Companion.REPLACE
import androidx.room.Query
import androidx.room.Update

@Dao
interface PersonalGoalDetailsDao {

    @Query("SELECT * FROM PersonalGoalDetails where goalId = :goalId")
    fun getGoalDetails(goalId: Long): List<PersonalGoalDetails>

    @Insert(onConflict = REPLACE)
    fun insertGoalDetails(goalDetails: PersonalGoalDetails): Long

    @Update
    fun updateGoalDetails(goalDetails: PersonalGoalDetails)
}
