package com.example.cliwatchjc.data.challenges

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ChallengesDao {

    @Query("SELECT * FROM Challenges")
    suspend fun getAllChallenges(): List<AddChallenges>

    // Update the queries to use AddChallenges instead of Challenges
    @Query("UPDATE Challenges SET challengeStatus = :status WHERE challengesId = :challengeId")
    suspend fun setChallengeStatus(challengeId: Long, status: String)

    @Query("SELECT challengeStatus FROM Challenges WHERE challengesId = :challengeId")
    suspend fun getChallengeStatus(challengeId: Long): String?

    @Insert
    suspend fun insertChallenges(challenges: List<AddChallenges>)


}

