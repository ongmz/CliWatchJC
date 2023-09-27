package com.example.cliwatchjc.data.tracker

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface PersonalGoalDao {

    @Query("SELECT * FROM Tracker")
    fun getAllGoals(): List<PersonalGoal>

    @Query("SELECT * FROM Tracker WHERE id = :id")
    fun getGoal(id: Long): PersonalGoal


    @Insert
    fun insertGoal(personalGoal: PersonalGoal): Long

    @Update
    fun updateGoal(personalGoal: PersonalGoal)

    @Query("UPDATE Tracker SET isSelected = :isSelected")
    fun unSelectAllGoals(isSelected: Boolean)

    @Delete
    fun deleteGoal(personalGoal: PersonalGoal)

}
