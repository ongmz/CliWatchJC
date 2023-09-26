package com.example.cliwatchjc.data.education

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity
data class Article(
    @PrimaryKey(autoGenerate = true)
    val articleId: Long = 0L,
    val title: String,
    val content: String,
    val maxScore: Int = 5
)

@Entity(
    tableName = "Question",
    foreignKeys = [ForeignKey(entity = Article::class, parentColumns = ["articleId"], childColumns = ["articleId"])]
)
data class Question(
    @PrimaryKey(autoGenerate = true)
    val questionId: Long = 0L,
    val articleId: Long,
    val text: String
)

@Entity(
    tableName = "Option",
    foreignKeys = [ForeignKey(entity = Question::class, parentColumns = ["questionId"], childColumns = ["questionId"])]
)
data class Option(
    @PrimaryKey(autoGenerate = true)
    val optionId: Long = 0L,
    val questionId: Long,
    val text: String,
    val isCorrect: Boolean
)

