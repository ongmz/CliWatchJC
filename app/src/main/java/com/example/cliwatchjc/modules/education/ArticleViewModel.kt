package com.example.cliwatchjc.modules.education

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cliwatchjc.UserManager
import com.example.cliwatchjc.data.User
import com.example.cliwatchjc.data.education.Article
import com.example.cliwatchjc.data.education.ArticleContentProvider
import com.example.cliwatchjc.data.education.Option
import com.example.cliwatchjc.data.education.Question
import com.example.cliwatchjc.data.education.UserQuizScore
import com.example.cliwatchjc.data.education.repository.ArticleRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ArticleViewModel @Inject constructor(
    private val articleRepository: ArticleRepository,
    private val userManager: UserManager
) : ViewModel() {

    private val _articles = MutableStateFlow<List<Article>>(emptyList())
    val articles: StateFlow<List<Article>> = _articles.asStateFlow()

    // This is to hold the quiz questions & options for a specific article
    private val _quizData = MutableStateFlow<List<QuestionWithOptions>>(emptyList())
    val quizData: StateFlow<List<QuestionWithOptions>> = _quizData.asStateFlow()

    /*
    // This is to hold the user's score for a specific article's quiz
    private val _userScore = MutableStateFlow<UserQuizScore?>(null)
    val userScore: StateFlow<UserQuizScore?> = _userScore.asStateFlow()
     */

    private val _userScores = MutableStateFlow<Map<Long, UserQuizScore>>(emptyMap())
    val userScores: StateFlow<Map<Long, UserQuizScore>> = _userScores.asStateFlow()

    val currentUser: StateFlow<User?> = userManager.currentUser
    val userTotalScore: StateFlow<Int> = MutableStateFlow(0)
    val totalQuestions: StateFlow<Int> = MutableStateFlow(0)

    init {
        loadArticles()
        loadScoresAndQuestions()
    }

    fun populateEducationEntity() {
        viewModelScope.launch {
            articleRepository.insertSampleArticles()
            articleRepository.insertSampleQuestionsWithOptions()
        }
    }

    private fun loadArticles() {
        viewModelScope.launch {
            val fetchedArticles = articleRepository.getAllArticles()
            _articles.emit(fetchedArticles)
        }
    }

    private fun loadScoresAndQuestions() {
        viewModelScope.launch {
            currentUser.value?.let {
                val userScore = articleRepository.getUserTotalScore(it.userId)
                (userTotalScore as MutableStateFlow).value = userScore

                val questionsCount = articleRepository.getTotalQuestionsCount()
                (totalQuestions as MutableStateFlow).value = questionsCount
            }
        }
    }

    fun getArticle(articleId: Long): Article? {
        return _articles.value.find { it.articleId == articleId }
    }

    fun getArticleContent(articleId: Long): List<ArticleContentComponent>? {
        return ArticleContentProvider.getContentByArticleId(articleId)
    }

    fun loadQuizDataForArticle(articleId: Long) {
        viewModelScope.launch {
            val questions = articleRepository.getQuestionsForArticle(articleId)
            val questionsWithOptions = questions.map { question ->
                val options = articleRepository.getOptionsForQuestion(question.questionId)
                QuestionWithOptions(question, options)
            }
            _quizData.emit(questionsWithOptions)
        }
    }

    fun fetchUserScoreForArticle(userId: Int, articleId: Long) {
        viewModelScope.launch {
            val score = articleRepository.getUserScoreForArticle(userId, articleId)
            Log.d("ArticleViewModel", "Fetched user score for user $userId and article $articleId: $score")

            // Update the map with the new score.
            val updatedScores = _userScores.value.toMutableMap()
            if (score != null) {
                updatedScores[articleId] = score
            } else {
                updatedScores.remove(articleId)
            }
            _userScores.emit(updatedScores)
        }
    }


    fun updateUserScore(articleId: Long, score: Int) {
        viewModelScope.launch {
            val user = currentUser.value
            user?.let {
                val userScore = UserQuizScore(it.userId, articleId, score)
                articleRepository.insertOrUpdateUserScore(userScore)

                // Update the map with the new score.
                val updatedScores = _userScores.value.toMutableMap()
                updatedScores[articleId] = userScore
                _userScores.emit(updatedScores)
            }
        }
    }


    data class QuestionWithOptions(val question: Question, val options: List<Option>)
}







