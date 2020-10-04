package com.example.weekfiveproject

import java.io.Serializable


data class Fruit(
    var id: Int,
    var title: String,
    var description: String,
    var image: String,
    var currentPrice: Float,
    var initialPrice: Float,
    var sale: String?,
    var isFavourite: Boolean
) : Serializable