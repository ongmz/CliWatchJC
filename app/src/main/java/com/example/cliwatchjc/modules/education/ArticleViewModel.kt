package com.example.cliwatchjc.modules.education

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cliwatchjc.UserManager
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

    // This is to hold the user's score for a specific article's quiz
    private val _userScore = MutableStateFlow<UserQuizScore?>(null)
    val userScore: StateFlow<UserQuizScore?> = _userScore.asStateFlow()

    val currentUser = userManager.currentUser

    init {
        loadArticles()
    }

    fun populateEducationEntity() {
        viewModelScope.launch {
            articleRepository.insertSampleArticles()
            articleRepository.insertSampleQuestionsWithOptions()
        }
    }

    fun loadUserScoreForArticle(articleId: Long) = viewModelScope.launch(Dispatchers.IO) {
        currentUser?.let { user ->
            val score = articleRepository.getUserScoreForArticle(user.userId, articleId)
            _userScore.emit(score)
        }
    }

    private fun loadArticles() = viewModelScope.launch(Dispatchers.IO) {
        val fetchedArticles = articleRepository.getAllArticles()
        _articles.emit(fetchedArticles)
    }

    fun getArticle(articleId: Long): Article? {
        return _articles.value.find { it.articleId == articleId }
    }

    fun getArticleContent(articleId: Long): List<ArticleContentComponent>? {
        return ArticleContentProvider.getContentByArticleId(articleId)
    }

    fun loadQuizDataForArticle(articleId: Long) = viewModelScope.launch(Dispatchers.IO) {
        val questions = articleRepository.getQuestionsForArticle(articleId)
        val questionsWithOptions = questions.map { question ->
            val options = articleRepository.getOptionsForQuestion(question.questionId)
            QuestionWithOptions(question, options)
        }
        _quizData.emit(questionsWithOptions)
    }

    /*
    fun loadUserScoreForArticle(userId: Long, articleId: Long) = viewModelScope.launch(Dispatchers.IO) {
        val score = educationRepository.getUserScoreForArticle(userId, articleId)
        _userScore.emit(score)
    }
    */
    fun getUserScoreForArticle(userId: Long, articleId: Long): Flow<UserQuizScore?> {
        return flow {
            val score = articleRepository.getUserScoreForArticle(userId, articleId)
            Log.d("ArticleViewModel", "Fetched user score for user $userId and article $articleId: $score")
            emit(score)
        }.flowOn(Dispatchers.IO)
    }


    fun updateUserScore(articleId: Long, score: Int) = viewModelScope.launch(Dispatchers.IO) {
        currentUser?.let { user ->
            val userScore = UserQuizScore(user.userId, articleId, score)
            articleRepository.insertOrUpdateUserScore(userScore)
            _userScore.emit(userScore)  // Update the score state for UI reflection
        }
    }



    // Add more functions as necessary, like handling quiz submission, calculating score, etc.

    data class QuestionWithOptions(val question: Question, val options: List<Option>)
}







