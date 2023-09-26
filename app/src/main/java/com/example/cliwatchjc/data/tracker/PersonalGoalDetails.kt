package com.example.cliwatchjc.data.tracker

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.util.*

@Entity(
    foreignKeys = [ForeignKey(
        entity = PersonalGoal::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("goalId"),
        onDelete = ForeignKey.CASCADE
    )]
)
data class PersonalGoalDetails(
    var goalId: Long = 0,
    var notes: String = "",
    var date: Date = Date()
) {
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
}
