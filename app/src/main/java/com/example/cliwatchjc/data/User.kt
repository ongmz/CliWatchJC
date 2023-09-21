package com.example.cliwatchjc.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String
    // Add other fields if needed
)