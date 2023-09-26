package com.example.cliwatchjc.data.challenges
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface ChallengesDao {

    @Query("SELECT * FROM Challenges")
    fun getAllChallenges(): List<AddChallenges>

    @Query("UPDATE ChallengeStatusEntity SET challengeStatus = :status WHERE challengesId = :challengeId")
    fun setChallengeStatus(challengeId: Long, status: String)

    @Insert
    fun insertChallenges(challenges: List<AddChallenges>)

    @Query("SELECT challengeStatus FROM ChallengeStatusEntity WHERE challengesId = :challengeId")
    fun getChallengeStatus(challengeId: Long): String?

    @Insert
    fun insertChallengeStatus(status: ChallengeStatusEntity)

    @Update
    fun updateChallengeStatus(status: ChallengeStatusEntity)
}





