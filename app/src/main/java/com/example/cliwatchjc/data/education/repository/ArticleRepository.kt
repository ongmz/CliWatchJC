package com.example.cliwatchjc.data.education.repository

import android.util.Log
import com.example.cliwatchjc.data.education.Article
import com.example.cliwatchjc.data.education.EducationDao
import com.example.cliwatchjc.data.education.Option
import com.example.cliwatchjc.data.education.Question
import com.example.cliwatchjc.data.education.UserQuizScore
import kotlin.math.max

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

    // Function to insert default/sample articles into the database
    fun insertSampleArticles() {
        val articles = listOf(
            Article(articleId = 1, title = "Climate Change Decoded", content = "The Basics and Beyond", maxScore = 3),
            Article(articleId = 2, title = "Ripples of a Warming World", content = "Far-reaching Consequences of Climate Change", maxScore = 3),
            Article(articleId = 3, title = "Leading the Charge", content = "How We Can Combat Climate Change", maxScore = 5)
        )
        for (article in articles) {
            educationDao.insertArticle(article)
        }
    }

    fun insertSampleQuestionsWithOptions() {
        val questionsWithOptions = listOf(
            Pair(
                Question(questionId = 1, articleId = 1, text = "What is responsible for the enhanced greenhouse effect?"),
                listOf(
                    Option(optionId = 1, questionId = 1, text = "Natural planetary cycle", isCorrect = false),
                    Option(optionId = 2, questionId = 1, text = "Solely the sun's activity", isCorrect = false),
                    Option(optionId = 3, questionId = 1, text = "Human activities like burning fossil fuels", isCorrect = true)
                )
            ),
            Pair(
                Question(questionId = 2, articleId = 1, text = "Which activity does NOT contribute significantly to CO2 emissions?"),
                listOf(
                    Option(optionId = 4, questionId = 2, text = "Breathing", isCorrect = true),
                    Option(optionId = 5, questionId = 2, text = "Burning coal", isCorrect = false),
                    Option(optionId = 6, questionId = 2, text = "Deforestation", isCorrect = false)
                )
            ),
            Pair(
                Question(questionId = 3, articleId = 1, text = "What effect does ocean acidification have?"),
                listOf(
                    Option(optionId = 7, questionId = 3, text = "Godzilla", isCorrect = false),
                    Option(optionId = 8, questionId = 3, text = "Strengthen coral reefs", isCorrect = false),
                    Option(optionId = 9, questionId = 3, text = "Endangers marine life", isCorrect = true)
                )
            ),
            /*
            Pair(
                Question(questionId = 4, articleId = 2, text = ""),
                listOf(
                    Option(optionId = 8, questionId = 1, text = "", isCorrect = true),
                    Option(optionId = 8, questionId = 1, text = "", isCorrect = false),
                    Option(optionId = 8, questionId = 1, text = "", isCorrect = false)
                )

            ),*/
            // ... add more sample questions with options as needed
        )

        // Insert questions and their associated options
        questionsWithOptions.forEach { (question, options) ->
            val newQuestionId = educationDao.insertQuestionAndGetId(question)
            options.forEach { option ->
                val newOption = option.copy(questionId = newQuestionId)
                educationDao.insertOption(newOption)
            }
        }
    }

}
