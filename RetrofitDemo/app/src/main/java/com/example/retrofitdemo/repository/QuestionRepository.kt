package com.example.retrofitdemo.repository

import com.example.retrofitdemo.remote.APIService


class QuestionRepository(private val apiService: APIService) {
    suspend fun fetchQuestions(tag: String) = apiService.fetchQuestions(tag)
}