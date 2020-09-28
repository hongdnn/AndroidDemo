package com.example.weekfiveproject

data class Fruit(
    val id: Int,
    val title: String,
    val description: String,
    val image: String,
    val currentPrice: Double,
    val initialPrice: Double,
    val sale: String?
)