package com.example.cliwatchjc.data.education

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface EducationDao {
    @Query("SELECT * FROM Article")
    fun getAllArticle(): List<Article>

    @Query("SELECT * FROM Article WHERE articleId = :id")
    fun getArticle(id: Long): Article

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertArticle(article: Article)

    @Query("SELECT * FROM UserQuizScore WHERE userId = :userId AND articleId = :articleId")
    fun getUserScoreForArticle(userId: Long, articleId: Long): UserQuizScore?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertOrUpdateUserScore(userScore: UserQuizScore)

    @Delete
    fun deleteArticle(article: Article)
}
