package com.example.weekfiveproject

import java.io.Serializable


data class Fruit(
    val id: Int,
    val title: String,
    val description: String,
    val image: String,
    val currentPrice: Float,
    val initialPrice: Float,
    val sale: String?
) :Serializable