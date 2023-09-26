package com.example.cliwatchjc.data.challenges


import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ChallengeStatus")
data class ChallengeStatusEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,
    val challengeId: Long,  // Reference to the challenge
    val challengeStatus: String
)
