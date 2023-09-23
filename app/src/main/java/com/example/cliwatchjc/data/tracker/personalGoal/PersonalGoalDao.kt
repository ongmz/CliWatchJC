package com.example.cliwatchjc.data.tracker.personalGoal

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.Companion.REPLACE
import androidx.room.Query
import androidx.room.Update

@Dao
interface PersonalGoalDao {

    @Query("SELECT * FROM PersonalGoal order by id")
    fun getAllGoals(): List<PersonalGoal>

    @Insert(onConflict = REPLACE)
    fun insertGoal(personalGoal: PersonalGoal): Long

    @Update
    fun updateGoal(personalGoal: PersonalGoal)

    @Query("UPDATE PersonalGoal SET isSelected = :isSelected")
    fun unSelectAllGoals(isSelected: Boolean)

    @Delete
    fun deleteGoal(personalGoal: PersonalGoal)
}
