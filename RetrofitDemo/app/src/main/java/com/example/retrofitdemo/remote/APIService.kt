package com.example.retrofitdemo.remote

import com.example.retrofitdemo.entity.QuestionList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface APIService {
    @GET("/2.2/questions?order=desc&sort=creation&site=stackoverflow")
    suspend fun fetchQuestions(@Query("tagged") tags: String): Response<QuestionList>
    //fun fetchQuestions(@Query("tagged") tags: String): Call<QuestionList>
}
