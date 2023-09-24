package com.example.cliwatchjc.data.challenges

import androidx.room.Dao
import androidx.room.Query

@Dao
interface ChallengesDao {


    @Query("SELECT * FROM Challenges")
    suspend fun getAllChallenges(): List<Challenges>

    @Query("UPDATE Challenges SET challengeStatus = :status WHERE challengesId = :challengeId AND userId = :userId")
    suspend fun setChallengeStatus(userId: Long, challengeId: Long, status: String)

    @Query("SELECT challengeStatus FROM Challenges WHERE challengesId = :challengeId AND userId = :userId")
    suspend fun getChallengeStatus(userId: Long, challengeId: Long): String?

}
