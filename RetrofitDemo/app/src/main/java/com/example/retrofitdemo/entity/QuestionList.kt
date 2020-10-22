package com.example.retrofitdemo.entity

data class QuestionList (
    val items: List<Question>,
    val has_more: Boolean,
    val quota_max: Number,
    val quota_remaining: Number
)
