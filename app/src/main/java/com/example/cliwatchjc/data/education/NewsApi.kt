package com.example.cliwatchjc.data.education

import retrofit2.http.GET
import retrofit2.Call

interface NewsApi {
    @GET("everything?q=climate")
    suspend fun getClimateNews(): Call<NewsResponse>
}
