package com.example.cliwatchjc.data.challenges


import androidx.room.Entity
import androidx.room.ForeignKey
import com.example.cliwatchjc.data.User

@Entity(
    foreignKeys = [
        ForeignKey(entity = User::class, parentColumns = ["userId"], childColumns = ["userId"]),
        ForeignKey(entity = AddChallenges::class, parentColumns = ["challengesId"], childColumns = ["challengesId"])
    ],
    primaryKeys = ["userId", "challengesId"] // Define composite primary key here
)
data class ChallengeStatusEntity(
    val userId: Long,
    val challengesId: Long,
    val challengeStatus: String
)
