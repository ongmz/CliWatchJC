package com.example.cliwatchjc.data.tracker

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface PersonalGoalDao {

    @Query("SELECT * FROM PersonalGoal")
    suspend fun getAllGoals(): List<PersonalGoal>

    @Query("SELECT * FROM PersonalGoal WHERE id = :id")
    suspend fun getGoal(id: Long): PersonalGoal

    @Query("SELECT * FROM PersonalGoal WHERE title = :title")
    suspend fun getGoalByTitle(title: String): PersonalGoal?

    @Insert
    suspend fun insertGoal(personalGoal: PersonalGoal): Long

    @Update
    suspend fun updateGoal(personalGoal: PersonalGoal)

    @Query("UPDATE PersonalGoal SET isSelected = :isSelected")
    suspend fun unSelectAllGoals(isSelected: Boolean)

    @Delete
    suspend fun deleteGoal(personalGoal: PersonalGoal)

}
