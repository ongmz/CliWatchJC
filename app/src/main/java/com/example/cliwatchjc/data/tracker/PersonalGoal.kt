package com.example.cliwatchjc.data.tracker

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "PersonalGoal")
data class PersonalGoal(
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0L,
    val title: String = "",
    val description: String,
    val dueDate: Long? = null, // Add dueDate field
    var completed: Boolean = false, // Add a completed property
    var completedDays: Int = 0,
    val totalDays: Int = 0,
    var isSelected: Boolean = true,
    val hasNotes: Boolean = false,
    val createdDate: Date = Date()
)
