package com.example.cliwatchjc.data.education.repository

import com.example.cliwatchjc.data.education.ClimateNews
import com.example.cliwatchjc.data.education.EducationDao
import com.example.cliwatchjc.data.education.NewsApi
import retrofit2.awaitResponse


class NewsRepository(private val newsApi: NewsApi, private val educationDao: EducationDao) {

    fun getClimateNews(): List<ClimateNews> {
        val call = newsApi.getClimateNews()
        val response = call.execute() // synchronous call

        if (response.isSuccessful) {
            return response.body()?.articles ?: listOf()
        } else {
            // handle error or return empty list
            return listOf()
        }
    }
}
