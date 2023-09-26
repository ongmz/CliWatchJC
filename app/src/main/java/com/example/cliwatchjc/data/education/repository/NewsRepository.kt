package com.example.cliwatchjc.data.education.repository

import com.example.cliwatchjc.AppModule.API_KEY
import com.example.cliwatchjc.data.education.Article
import com.example.cliwatchjc.data.education.ClimateNews
import com.example.cliwatchjc.data.education.EducationDao
import com.example.cliwatchjc.data.education.NewsApi

class NewsRepository(private val newsApi: NewsApi, private val educationDao: EducationDao) {

    fun getClimateNews(): List<ClimateNews> {
        // Fetch from API
        val response = newsApi.getClimateNews(API_KEY)

        // Optional: Save to database or handle other logic
        // For example:
        // educationDao.insertAll(response.articles)
        // Note: You might want to use another DAO for this operation,
        // 'educationDao' seems to be unrelated to news.

        return response.news
    }
}