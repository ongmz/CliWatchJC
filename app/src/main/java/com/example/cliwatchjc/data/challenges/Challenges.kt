package com.example.cliwatchjc.data.challenges

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Challenges")
data class Challenges(
    @PrimaryKey(autoGenerate = true)
    val challengesId: Long = 0L,
    val title: String,
    val content: String,
    val maxScore: Int = 5
)