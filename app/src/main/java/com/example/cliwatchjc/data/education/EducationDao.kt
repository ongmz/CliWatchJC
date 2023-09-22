package com.example.cliwatchjc.data.education

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface EducationDao {
    @Query("SELECT * FROM article")
    fun getAllArticle(): List<Article>

    @Query("SELECT * FROM article WHERE articleId = :id")
    fun getArticle(id: Long): Article

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertArticle(article: Article)

    @Query("SELECT * FROM user_quiz_score WHERE userId = :userId AND articleId = :articleId")
    fun getUserScoreForArticle(userId: Long, articleId: Long): UserQuizScore?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertOrUpdateUserScore(userScore: UserQuizScore)

    @Delete
    fun deleteArticle(article: Article)
}
