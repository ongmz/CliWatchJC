package com.example.cliwatchjc.data.education

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface EducationDao {

    // Methods for Article
    @Query("SELECT * FROM Article")
    suspend fun getAllArticles(): List<Article>

    @Query("SELECT * FROM Article WHERE articleId = :id")
    suspend fun getArticleById(id: Long): Article

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertArticle(article: Article)

    @Delete
    suspend fun deleteArticle(article: Article)

    // Methods for Question
    @Query("SELECT * FROM Question WHERE articleId = :articleId")
    suspend fun getQuestionsForArticle(articleId: Long): List<Question>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertQuestion(question: Question)

    @Delete
    suspend fun deleteQuestion(question: Question)

    @Query("SELECT COUNT(*) FROM Question")
    suspend fun getQuestionsCount(): Int

    // Methods for Option
    @Query("SELECT * FROM Option WHERE questionId = :questionId")
    suspend fun getOptionsForQuestion(questionId: Long): List<Option>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOption(option: Option)

    @Delete
    suspend fun deleteOption(option: Option)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertQuestionAndGetId(question: Question): Long

    // Methods for UserQuizScore
    @Query("SELECT * FROM UserQuizScore WHERE userId = :userId AND articleId = :articleId")
    suspend fun getUserScoreForArticle(userId: Int, articleId: Long): UserQuizScore?

    @Query("SELECT SUM(score) FROM UserQuizScore WHERE userId = :userId")
    suspend fun getUserTotalScore(userId: Int): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrUpdateUserScore(userScore: UserQuizScore)

    @Delete
    suspend fun deleteUserQuizScore(userScore: UserQuizScore)

    @Query("SELECT COUNT(*) FROM Question WHERE articleId = :articleId")
    suspend fun getTotalQuestionsForArticle(articleId: Long): Int

    @Query("""
    SELECT COUNT(*) FROM UserQuestionAttempt
    WHERE userId = :userId AND questionId IN (SELECT questionId FROM Question WHERE articleId = :articleId) AND isCorrect = 1
    """)
    suspend fun getCorrectAnswersForArticle(userId: Long, articleId: Long): Int

    // Methods for News
    @Query("SELECT * FROM ClimateNews")
    suspend fun getAllNews(): List<ClimateNews>

    @Insert
    suspend fun insert(news: ClimateNews)
}
