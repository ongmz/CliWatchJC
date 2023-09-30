package com.example.cliwatchjc.data.challenges

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow


@Dao
interface ChallengesDao {
    @Query("SELECT * FROM Challenges")
    suspend fun getAllChallenges(): List<Challenges>

    @Query("SELECT * FROM Challenges WHERE challengesId = :id")
    suspend fun getChallengesById(id: Long): Challenges

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertChallenges(challenges: Challenges)

    @Delete
    suspend fun deleteChallenges(challenges: Challenges)
}


