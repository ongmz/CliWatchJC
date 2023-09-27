package com.example.cliwatchjc.data.challenges

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Challenges")
data class AddChallenges(
    @PrimaryKey(autoGenerate = true)
    val challengesId: Long,
    val challenges_title:String,
    val challenges_desc:String,
    val challenges_duration:String,
    val challenges_status:String,
    val marks: Int = 0
)