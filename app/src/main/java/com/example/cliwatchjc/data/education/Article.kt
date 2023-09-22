package com.example.cliwatchjc.data.education

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "article")
data class Article(
    @PrimaryKey(autoGenerate = true)
    val articleId: Long = 0L,
    val title: String,
    val content: String,
    val maxScore: Int = 5
)
