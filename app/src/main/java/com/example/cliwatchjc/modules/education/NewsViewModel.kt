package com.example.cliwatchjc.modules.education

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cliwatchjc.UserManager
import com.example.cliwatchjc.data.education.Article
import com.example.cliwatchjc.data.education.ClimateNews
import com.example.cliwatchjc.data.education.repository.ArticleRepository
import com.example.cliwatchjc.data.education.repository.NewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val newsRepository: NewsRepository,
) : ViewModel() {

    private val _newsList = MutableStateFlow<List<ClimateNews>>(emptyList())
    val newsList: StateFlow<List<ClimateNews>> = _newsList

    init {
        fetchNews()
    }

    private fun fetchNews() {
        viewModelScope.launch(Dispatchers.IO) {
            _newsList.value = newsRepository.getClimateNews()
        }
    }
}