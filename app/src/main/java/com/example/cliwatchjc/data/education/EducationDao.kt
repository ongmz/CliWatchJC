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
    fun getAllArticles(): List<Article>

    @Query("SELECT * FROM Article WHERE articleId = :id")
    fun getArticleById(id: Long): Article

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertArticle(article: Article)

    @Delete
    fun deleteArticle(article: Article)

    // Methods for Question
    @Query("SELECT * FROM Question WHERE articleId = :articleId")
    fun getQuestionsForArticle(articleId: Long): List<Question>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertQuestion(question: Question)

    @Delete
    fun deleteQuestion(question: Question)

    // Methods for Option
    @Query("SELECT * FROM Option WHERE questionId = :questionId")
    fun getOptionsForQuestion(questionId: Long): List<Option>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertOption(option: Option)

    @Delete
    fun deleteOption(option: Option)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertQuestionAndGetId(question: Question): Long

    // Methods for UserQuizScore
    @Query("SELECT * FROM UserQuizScore WHERE userId = :userId AND articleId = :articleId")
    fun getUserScoreForArticle(userId: Long, articleId: Long): UserQuizScore?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertOrUpdateUserScore(userScore: UserQuizScore)

    @Delete
    fun deleteUserQuizScore(userScore: UserQuizScore)

    @Query("SELECT COUNT(*) FROM Question WHERE articleId = :articleId")
    fun getTotalQuestionsForArticle(articleId: Long): Int

    @Query("""
    SELECT COUNT(*) FROM UserQuestionAttempt
    WHERE userId = :userId AND questionId IN (SELECT questionId FROM Question WHERE articleId = :articleId) AND isCorrect = 1
    """)
    fun getCorrectAnswersForArticle(userId: Long, articleId: Long): Int

    // Methods for News
    @Query("SELECT * FROM ClimateNews")
    fun getAllNews(): List<ClimateNews>

    @Insert
    fun insert(news: ClimateNews)
}

