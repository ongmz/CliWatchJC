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
val adminChallenges = listOf(
    AddChallenges(1L, "title 1", "Description 1", "week", "Not started"),
    AddChallenges(2L, "title 2", "Description 2", "week", "In progress"),
    AddChallenges(3L, "title 3", "Description 3", "week", "Completed")
)
