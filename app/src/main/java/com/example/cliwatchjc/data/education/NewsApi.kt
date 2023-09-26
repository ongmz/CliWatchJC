package com.example.cliwatchjc.data.education

import com.example.cliwatchjc.AppModule.API_KEY
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {
    @GET("everything?q=climate")
    fun getClimateNews(@Query("apiKey") apiKey: String): NewsResponse
}