package com.example.cliwatchjc.data.trackerData.personalGoal

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.Companion.REPLACE
import androidx.room.Query
import androidx.room.Update
import com.example.cliwatchjc.data.trackerData.personalGoal.PersonalGoalDetails

@Dao
interface PersonalGoalDetailsDAO {

    @Query("SELECT * FROM PersonalGoalDetails where goalId = :goalId")
    fun getGoalDetails(goalId: Long): LiveData<List<PersonalGoalDetails>>

    @Insert(onConflict = REPLACE)
    fun insertGoalDetails(goalDetails: PersonalGoalDetails): Long

    @Update
    fun updateGoalDetails(goalDetails: PersonalGoalDetails)
}