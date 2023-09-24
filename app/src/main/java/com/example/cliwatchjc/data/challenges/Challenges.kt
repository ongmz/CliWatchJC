package com.example.cliwatchjc.data.challenges

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Challenges")
data class Challenges(
    @PrimaryKey(autoGenerate = true)
    val challengesId: Long = 0L,
    val challenges_title:String,
    val challenges_desc:String,
    val challenges_duration:String,
    val Score: Int = 5,
    val challengeStatus: String,
    val userId: Long
)
