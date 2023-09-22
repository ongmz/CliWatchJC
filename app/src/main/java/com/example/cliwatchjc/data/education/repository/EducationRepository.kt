package com.example.cliwatchjc.data.education.repository

import com.example.cliwatchjc.data.education.Article
import com.example.cliwatchjc.data.education.EducationDao
import com.example.cliwatchjc.data.education.UserQuizScore

class ArticleRepository(private val educationDao: EducationDao) {
    // Methods for articles
    fun getAllArticles(): List<Article> {
        return educationDao.getAllArticle()
    }

    fun getArticle(id: Long): Article {
        return educationDao.getArticle(id)
    }

    fun insertArticle(article: Article) {
        educationDao.insertArticle(article)
    }

    fun deleteArticle(article: Article) {
        educationDao.deleteArticle(article)
    }

    // Methods for user quiz scores related to articles
   fun getUserScoreForArticle(userId: Long, articleId: Long): UserQuizScore? {
        return educationDao.getUserScoreForArticle(userId, articleId)
    }

    fun insertOrUpdateUserScore(userScore: UserQuizScore) {
        educationDao.insertOrUpdateUserScore(userScore)
    }

}

/*
class NewsRepository(private val educationDao: EducationDao) {
    suspend fun getAllNewsReports(): List<NewsReport> {
        // Implement the logic to fetch news reports here
    }

    // Add methods for creating, updating, and deleting news reports as needed
}
*/