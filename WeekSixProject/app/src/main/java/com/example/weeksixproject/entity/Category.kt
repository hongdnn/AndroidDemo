package com.example.weeksixproject.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "category")

class Category(
    @PrimaryKey @ColumnInfo(name = "categoryName") val categoryName: String

)