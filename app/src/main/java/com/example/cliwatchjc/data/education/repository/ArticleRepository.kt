package com.example.cliwatchjc.data.education.repository

import android.util.Log
import com.example.cliwatchjc.data.education.Article
import com.example.cliwatchjc.data.education.EducationDao
import com.example.cliwatchjc.data.education.Option
import com.example.cliwatchjc.data.education.Question
import com.example.cliwatchjc.data.education.UserQuizScore

class ArticleRepository(private val educationDao: EducationDao) {

    // Methods for articles
    fun getAllArticles(): List<Article> = educationDao.getAllArticles()

    fun getArticleById(id: Long): Article = educationDao.getArticleById(id)

    fun insertArticle(article: Article) = educationDao.insertArticle(article)

    fun deleteArticle(article: Article) = educationDao.deleteArticle(article)

    // Methods for questions
    fun getQuestionsForArticle(articleId: Long): List<Question> = educationDao.getQuestionsForArticle(articleId)

    fun insertQuestion(question: Question) = educationDao.insertQuestion(question)

    fun deleteQuestion(question: Question) = educationDao.deleteQuestion(question)

    // Methods for options
    fun getOptionsForQuestion(questionId: Long): List<Option> = educationDao.getOptionsForQuestion(questionId)

    fun insertOption(option: Option) = educationDao.insertOption(option)

    fun deleteOption(option: Option) = educationDao.deleteOption(option)

    // Methods for user quiz scores
    //fun getUserScoreForArticle(userId: Long, articleId: Long): UserQuizScore? = educationDao.getUserScoreForArticle(userId, articleId)
    fun getUserScoreForArticle(userId: Long, articleId: Long): UserQuizScore? {
        val score = educationDao.getUserScoreForArticle(userId, articleId)
        Log.d("EducationRepository", "Fetched user score for user $userId and article $articleId: $score")
        return score
    }

    fun insertOrUpdateUserScore(userScore: UserQuizScore) = educationDao.insertOrUpdateUserScore(userScore)

    fun deleteUserQuizScore(userScore: UserQuizScore) = educationDao.deleteUserQuizScore(userScore)

    fun getTotalQuestionsForArticle(articleId: Long): Int = educationDao.getTotalQuestionsForArticle(articleId)

    fun getCorrectAnswersForArticle(userId: Long, articleId: Long): Int = educationDao.getCorrectAnswersForArticle(userId, articleId)

}


/*
class NewsRepository(private val educationDao: EducationDao) {
    suspend fun getAllNewsReports(): List<NewsReport> {
        // Implement the logic to fetch news reports here
    }

    // Add methods for creating, updating, and deleting news reports as needed
}
*/