package com.example.cliwatchjc.data.challenges

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface ChallengesDao {

    @Query("SELECT * FROM Challenges")
    suspend fun getAllChallenges(): List<AddChallenges>

    // Update the queries to use ChallengesProgress instead of AddChallenges
    @Query("UPDATE ChallengeStatus SET challengeStatus = :status WHERE challengeId = :challengeId")
    suspend fun setChallengeStatus(challengeId: Long, status: String)

    @Insert
    suspend fun insertChallenges(challenges: List<AddChallenges>)

    // Add methods to manage ChallengeStatusEntity
    @Query("SELECT challengeStatus FROM ChallengeStatus WHERE challengeId = :challengeId")
    suspend fun getChallengeStatus(challengeId: Long): String?

    @Insert
    suspend fun insertChallengeStatus(status: ChallengeStatusEntity)

    @Update
    suspend fun updateChallengeStatus(status: ChallengeStatusEntity)
}


