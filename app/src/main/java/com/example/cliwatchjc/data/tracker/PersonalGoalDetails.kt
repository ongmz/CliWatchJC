package com.example.cliwatchjc.data.tracker

import androidx.room.Entity
import androidx.room.ForeignKey
import com.example.cliwatchjc.data.User
import java.util.*

@Entity(
    foreignKeys = [
        ForeignKey(entity = User::class, parentColumns = ["userId"], childColumns = ["userId"]),
        ForeignKey(entity = PersonalGoal::class, parentColumns = ["id"], childColumns = ["goalId"])
    ],
    primaryKeys = ["userId", "goalId"]
)
data class PersonalGoalDetails(
    val userId: Long,
    var goalId: Long,
    var notes: String,
    var date: Date = Date()
)



