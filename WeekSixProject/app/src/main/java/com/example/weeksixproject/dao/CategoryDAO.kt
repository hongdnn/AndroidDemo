package com.example.weeksixproject.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.weeksixproject.entity.Category

@Dao
interface CategoryDAO {

    @Query("SELECT * from category ORDER BY categoryName ASC")
    fun getAllCategories(): LiveData<List<Category>>

    @Insert
    suspend fun insert(category: Category)

    @Query("SELECT * from category WHERE categoryName = :searchCategory")
     fun searchCategory(searchCategory: String): Category
}