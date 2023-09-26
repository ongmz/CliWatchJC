package com.example.cliwatchjc.data.challenges

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Challenges")
data class AddChallenges(
    @PrimaryKey(autoGenerate = true)
    val challengesId: Long = 0L,
    val challenges_title:String,
    val challenges_desc:String,
    val challenges_duration:String
)

data class ChallengesProgress(
    val challenges_title:String,
    val challenges_desc:String,
    val challenges_duration:String,
    val challengeStatus: String
)

data class LeaderboardEntity(
    val userId: Long,
    val challengesId: Long,
    val username: String,
    val score: Int
)
