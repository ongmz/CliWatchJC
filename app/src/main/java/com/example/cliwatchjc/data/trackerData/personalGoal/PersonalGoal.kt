package com.example.cliwatchjc.data.trackerData.personalGoal

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class PersonalGoal(
    val title: String = "",
    var completedDays: Int = 0,
    val totalDays: Int = 0,
    var isSelected: Boolean = true,
    val hasNotes: Boolean = false,
    val createdDate: Date = Date()
) {
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
}

fun PersonalGoal.isCompleted(): Boolean {
    return this.completedDays == this.totalDays
}
