package com.example.cliwatchjc.data.education

import androidx.room.Entity
import androidx.room.ForeignKey
import com.example.cliwatchjc.data.User

@Entity(
    foreignKeys = [
        ForeignKey(entity = User::class, parentColumns = ["userId"], childColumns = ["userId"]),
        ForeignKey(entity = Article::class, parentColumns = ["articleId"], childColumns = ["articleId"])
    ],
    primaryKeys = ["userId", "articleId"]
)
data class UserQuizScore(
    val userId: Long,
    val articleId: Long,
    val score: Int
)

@Entity(
    foreignKeys = [
        ForeignKey(entity = User::class, parentColumns = ["userId"], childColumns = ["userId"]),
        ForeignKey(entity = Question::class, parentColumns = ["questionId"], childColumns = ["questionId"])
    ],
    primaryKeys = ["userId", "questionId"]
)
data class UserQuestionAttempt(
    val userId: Long,
    val questionId: Long,
    val selectedOptionId: Long, // This represents the option that user selected
    val isCorrect: Boolean // Whether the user's attempt was correct or not
)
