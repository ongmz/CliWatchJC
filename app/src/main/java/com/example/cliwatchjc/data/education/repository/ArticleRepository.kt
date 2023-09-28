package com.example.cliwatchjc.data.education.repository

import android.util.Log
import com.example.cliwatchjc.data.education.Article
import com.example.cliwatchjc.data.education.EducationDao
import com.example.cliwatchjc.data.education.Option
import com.example.cliwatchjc.data.education.Question
import com.example.cliwatchjc.data.education.UserQuizScore

class ArticleRepository(private val educationDao: EducationDao) {

    // Methods for articles
    suspend fun getAllArticles(): List<Article> = educationDao.getAllArticles()

    suspend fun getArticleById(id: Long): Article = educationDao.getArticleById(id)

    suspend fun insertArticle(article: Article) = educationDao.insertArticle(article)

    suspend fun deleteArticle(article: Article) = educationDao.deleteArticle(article)

    // Methods for questions
    suspend fun getQuestionsForArticle(articleId: Long): List<Question> = educationDao.getQuestionsForArticle(articleId)

    suspend fun insertQuestion(question: Question) = educationDao.insertQuestion(question)

    suspend fun deleteQuestion(question: Question) = educationDao.deleteQuestion(question)

    suspend fun getTotalQuestionsCount(): Int = educationDao.getQuestionsCount()

    // Methods for options
    suspend fun getOptionsForQuestion(questionId: Long): List<Option> = educationDao.getOptionsForQuestion(questionId)

    suspend fun insertOption(option: Option) = educationDao.insertOption(option)

    suspend fun deleteOption(option: Option) = educationDao.deleteOption(option)

    // Methods for user quiz scores
    suspend fun getUserScoreForArticle(userId: Int, articleId: Long): UserQuizScore? {
        val score = educationDao.getUserScoreForArticle(userId, articleId)
        Log.d("EducationRepository", "Fetched user score for user $userId and article $articleId: $score")
        return score
    }

    suspend fun getUserTotalScore(userId: Int): Int = educationDao.getUserTotalScore(userId)

    suspend fun insertOrUpdateUserScore(userScore: UserQuizScore) = educationDao.insertOrUpdateUserScore(userScore)

    suspend fun deleteUserQuizScore(userScore: UserQuizScore) = educationDao.deleteUserQuizScore(userScore)

    suspend fun getTotalQuestionsForArticle(articleId: Long): Int = educationDao.getTotalQuestionsForArticle(articleId)

    suspend fun getCorrectAnswersForArticle(userId: Long, articleId: Long): Int = educationDao.getCorrectAnswersForArticle(userId, articleId)

    // Function to insert default/sample articles into the database
    suspend fun insertSampleArticles() {
        val articles = listOf(
            Article(articleId = 1, title = "Climate Change Decoded", content = "The Basics and Beyond", maxScore = 3),
            Article(articleId = 2, title = "Ripples of a Warming World", content = "Far-reaching Consequences of Climate Change", maxScore = 3),
            Article(articleId = 3, title = "Leading the Charge", content = "How We Can Combat Climate Change", maxScore = 5)
        )
        for (article in articles) {
            educationDao.insertArticle(article)
        }
    }

    suspend fun insertSampleQuestionsWithOptions() {
        val questionsWithOptions = listOf(
            //Article 1
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

            //Article 2
            Pair(
                Question(questionId = 4, articleId = 2, text = "What's a consequence of shifting habitats?"),
                listOf(
                    Option(optionId = 10, questionId = 4, text = "Increase in biodiversity", isCorrect = false),
                    Option(optionId = 11, questionId = 4, text = "New species creation", isCorrect = false),
                    Option(optionId = 12, questionId = 4, text = "Potential extinction of species", isCorrect = true)
                )
            ),
            Pair(
                Question(questionId = 5, articleId = 2, text = "Why are coastal cities at risk due to climate change?"),
                listOf(
                    Option(optionId = 13, questionId = 5, text = "Penang will sink in 2040?", isCorrect = true),
                    Option(optionId = 14, questionId = 5, text = "They are becoming too hot", isCorrect = false),
                    Option(optionId = 15, questionId = 5, text = "Due to increasing urbanization", isCorrect = false)
                )
            ),
            Pair(
                Question(questionId = 6, articleId = 2, text = "What happens to coral reefs as water temperatures rise?"),
                listOf(
                    Option(optionId = 16, questionId = 6, text = "They multiply rapidly", isCorrect = false),
                    Option(optionId = 17, questionId = 6, text = "They undergo bleaching", isCorrect = true),
                    Option(optionId = 18, questionId = 6, text = "Oh no Spongebob and Patrick", isCorrect = false)
                )
            ),
            Pair(
                Question(questionId = 7, articleId = 3, text = "What can make a substantial difference in reducing emissions in homes and offices?"),
                listOf(
                    Option(optionId = 19, questionId = 7, text = "Using more electronics", isCorrect = false),
                    Option(optionId = 20, questionId = 7, text = "Installing energy-efficient appliances", isCorrect = true),
                    Option(optionId = 21, questionId = 7, text = "Keeping windows open during winter", isCorrect = false)
                )
            ),
            Pair(
                Question(questionId = 8, articleId = 3, text = "Which activity aids in carbon absorption and fights the \"urban heat island\" effect?"),
                listOf(
                    Option(optionId = 22, questionId = 8, text = "Building taller skyscrapers", isCorrect = false),
                    Option(optionId = 23, questionId = 8, text = "Constructing more highways", isCorrect = false),
                    Option(optionId = 24, questionId = 8, text = "Developing urban green spaces", isCorrect = true)
                )
            ),

            //Article 3
            Pair(
                Question(questionId = 9, articleId = 3, text = "Which technology can actively pull CO2 out of the atmosphere and store it?"),
                listOf(
                    Option(optionId = 25, questionId = 9, text = "Tesla", isCorrect = false),
                    Option(optionId = 26, questionId = 9, text = "Solar panels", isCorrect = false),
                    Option(optionId = 27, questionId = 9, text = "Carbon capture and storage", isCorrect = true)
                )
            ),
            Pair(
                Question(questionId = 10, articleId = 3, text = "What role can governments play in promoting sustainable practices?"),
                listOf(
                    Option(optionId = 28, questionId = 10, text = "Offer incentives for burning coal", isCorrect = false),
                    Option(optionId = 29, questionId = 10, text = "Offer incentives for adopting green practices", isCorrect = true),
                    Option(optionId = 30, questionId = 10, text = "Reduce funding for renewable energy research", isCorrect = false)
                )
            ),
            Pair(
                Question(questionId = 11, articleId = 3, text = "Which of the following is NOT a form of sustainable renewable energy?"),
                listOf(
                    Option(optionId = 31, questionId = 11, text = "Fart gas", isCorrect = true),
                    Option(optionId = 32, questionId = 11, text = "Wind Power", isCorrect = false),
                    Option(optionId = 33, questionId = 11, text = "Solar Power", isCorrect = false)
                )
            ),
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
