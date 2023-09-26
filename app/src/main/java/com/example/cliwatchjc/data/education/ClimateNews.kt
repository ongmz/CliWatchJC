package com.example.cliwatchjc.data.education

import androidx.room.Entity
import androidx.room.PrimaryKey
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

@Entity(tableName = "ClimateNews")
data class ClimateNews (
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val title: String,
    val description: String?,
    val url: String,
    val urlToImage: String?
)

data class NewsResponse(
    val status: String,
    val totalResults: Int,
    val news: List<ClimateNews>
)










