package com.example.cliwatchjc.data.challenges

import androidx.room.Entity
import androidx.room.PrimaryKey

// LeaderboardEntry.kt
@Entity(tableName = "Leaderboard")
data class Leaderboard(
    @PrimaryKey(autoGenerate = true)
    val leaderboardId: Long = 0L,
    val username: String,
    val totalMarks: Int
)

val leaderboardData = listOf(
    Leaderboard(1L, "Nat", 100),
    Leaderboard(1L, "Jane", 80),
    Leaderboard(1L, "mic", 40),
    // Add more entries as needed
)
